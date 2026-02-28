/**
 * HEROES & VILLAINS - Application JavaScript
 * Modern ES6+ with best practices
 */

// ============================================
// 1. CONFIGURATION
// ============================================
const CONFIG = {
    API_BASE_URL: '/api/v1',
    DEBOUNCE_DELAY: 300,
    TOAST_DURATION: 5000,
    ANIMATION_DURATION: 300
};

// ============================================
// 2. UTILITAIRES
// ============================================

/**
 * Debounce function to limit API calls
 */
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

/**
 * Format date to locale string
 */
function formatDate(dateString) {
    return new Date(dateString).toLocaleDateString('fr-FR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });
}

/**
 * Format number with separators
 */
function formatNumber(num) {
    return new Intl.NumberFormat('fr-FR').format(num);
}

/**
 * Generate avatar initials from name
 */
function getInitials(name) {
    return name
        .split(' ')
        .map(n => n[0])
        .join('')
        .toUpperCase()
        .slice(0, 2);
}

// ============================================
// 3. TOAST NOTIFICATIONS
// ============================================
class ToastManager {
    constructor() {
        this.container = this.createContainer();
    }

    createContainer() {
        let container = document.getElementById('toast-container');
        if (!container) {
            container = document.createElement('div');
            container.id = 'toast-container';
            container.className = 'toast-container';
            document.body.appendChild(container);
        }
        return container;
    }

    show(message, type = 'info', duration = CONFIG.TOAST_DURATION) {
        const toast = document.createElement('div');
        toast.className = `toast ${type} animate-fade-in`;
        
        const icons = {
            success: 'fa-check-circle',
            error: 'fa-exclamation-circle',
            warning: 'fa-exclamation-triangle',
            info: 'fa-info-circle'
        };
        
        toast.innerHTML = `
            <i class="fas ${icons[type]}"></i>
            <span>${message}</span>
            <button class="btn-close" onclick="this.parentElement.remove()">
                <i class="fas fa-times"></i>
            </button>
        `;
        
        this.container.appendChild(toast);
        
        setTimeout(() => {
            toast.style.opacity = '0';
            toast.style.transform = 'translateX(100%)';
            setTimeout(() => toast.remove(), 300);
        }, duration);
    }
}

const toast = new ToastManager();

// ============================================
// 4. SIDEBAR MANAGEMENT
// ============================================
class SidebarManager {
    constructor() {
        this.sidebar = document.querySelector('.sidebar');
        this.toggleBtn = document.getElementById('sidebar-toggle');
        this.overlay = this.createOverlay();
        this.init();
    }

    createOverlay() {
        const overlay = document.createElement('div');
        overlay.className = 'sidebar-overlay';
        overlay.style.cssText = `
            position: fixed;
            inset: 0;
            background: rgba(0,0,0,0.5);
            z-index: 1020;
            opacity: 0;
            visibility: hidden;
            transition: all 0.3s ease;
        `;
        overlay.addEventListener('click', () => this.close());
        document.body.appendChild(overlay);
        return overlay;
    }

    init() {
        if (this.toggleBtn) {
            this.toggleBtn.addEventListener('click', () => this.toggle());
        }

        // Handle window resize
        window.addEventListener('resize', debounce(() => {
            if (window.innerWidth > 1024) {
                this.overlay.style.opacity = '0';
                this.overlay.style.visibility = 'hidden';
            }
        }, 100));
    }

    toggle() {
        this.sidebar.classList.toggle('open');
        const isOpen = this.sidebar.classList.contains('open');
        this.overlay.style.opacity = isOpen ? '1' : '0';
        this.overlay.style.visibility = isOpen ? 'visible' : 'hidden';
    }

    open() {
        this.sidebar.classList.add('open');
        this.overlay.style.opacity = '1';
        this.overlay.style.visibility = 'visible';
    }

    close() {
        this.sidebar.classList.remove('open');
        this.overlay.style.opacity = '0';
        this.overlay.style.visibility = 'hidden';
    }
}

// ============================================
// 5. SEARCH FUNCTIONALITY
// ============================================
class SearchManager {
    constructor() {
        this.searchInput = document.getElementById('search-input');
        this.searchResults = document.getElementById('search-results');
        this.init();
    }

    init() {
        if (this.searchInput) {
            this.searchInput.addEventListener('input', 
                debounce((e) => this.handleSearch(e.target.value), CONFIG.DEBOUNCE_DELAY)
            );

            // Close search on escape
            document.addEventListener('keydown', (e) => {
                if (e.key === 'Escape') {
                    this.clearSearch();
                }
            });
        }
    }

    async handleSearch(query) {
        if (!query || query.length < 2) {
            this.hideResults();
            return;
        }

        try {
            const response = await fetch(`${CONFIG.API_BASE_URL}/heroes/search?keyword=${encodeURIComponent(query)}`);
            const data = await response.json();
            this.displayResults(data);
        } catch (error) {
            console.error('Search error:', error);
        }
    }

    displayResults(results) {
        if (!results || results.length === 0) {
            this.searchResults.innerHTML = '<div class="p-3 text-gray-500">Aucun résultat trouvé</div>';
        } else {
            this.searchResults.innerHTML = results.map(item => `
                <a href="/heroes/${item.id}" class="d-flex align-items-center p-3 text-decoration-none border-bottom">
                    <div class="me-3">
                        <i class="fas fa-user-circle fa-2x text-primary"></i>
                    </div>
                    <div>
                        <div class="font-semibold">${item.heroName}</div>
                        <div class="text-sm text-gray-500">${item.firstName} ${item.lastName}</div>
                    </div>
                </a>
            `).join('');
        }
        this.searchResults.style.display = 'block';
    }

    hideResults() {
        if (this.searchResults) {
            this.searchResults.style.display = 'none';
        }
    }

    clearSearch() {
        if (this.searchInput) {
            this.searchInput.value = '';
        }
        this.hideResults();
    }
}

// ============================================
// 6. DATA TABLE MANAGEMENT
// ============================================
class DataTable {
    constructor(tableId, options = {}) {
        this.table = document.getElementById(tableId);
        this.options = {
            sortable: true,
            searchable: true,
            paginated: true,
            itemsPerPage: 10,
            ...options
        };
        this.currentPage = 1;
        this.init();
    }

    init() {
        if (!this.table) return;

        this.setupSorting();
        this.setupPagination();
    }

    setupSorting() {
        const headers = this.table.querySelectorAll('th[data-sort]');
        headers.forEach(header => {
            header.style.cursor = 'pointer';
            header.addEventListener('click', () => this.sort(header.dataset.sort));
        });
    }

    sort(column) {
        const tbody = this.table.querySelector('tbody');
        const rows = Array.from(tbody.querySelectorAll('tr'));
        
        rows.sort((a, b) => {
            const aVal = a.querySelector(`[data-column="${column}"]`).textContent;
            const bVal = b.querySelector(`[data-column="${column}"]`).textContent;
            return aVal.localeCompare(bVal);
        });

        rows.forEach(row => tbody.appendChild(row));
    }

    setupPagination() {
        // Pagination logic would go here
    }
}

// ============================================
// 7. FORM VALIDATION
// ============================================
class FormValidator {
    constructor(formId) {
        this.form = document.getElementById(formId);
        if (this.form) {
            this.init();
        }
    }

    init() {
        this.form.addEventListener('submit', (e) => this.handleSubmit(e));
        
        // Real-time validation
        this.form.querySelectorAll('input, textarea, select').forEach(field => {
            field.addEventListener('blur', () => this.validateField(field));
            field.addEventListener('input', () => this.clearError(field));
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        
        if (this.validate()) {
            this.form.submit();
        }
    }

    validate() {
        let isValid = true;
        this.form.querySelectorAll('[required]').forEach(field => {
            if (!field.value.trim()) {
                this.showError(field, 'Ce champ est requis');
                isValid = false;
            }
        });
        return isValid;
    }

    validateField(field) {
        if (field.hasAttribute('required') && !field.value.trim()) {
            this.showError(field, 'Ce champ est requis');
        }
    }

    showError(field, message) {
        field.classList.add('is-invalid');
        let errorDiv = field.nextElementSibling;
        if (!errorDiv || !errorDiv.classList.contains('invalid-feedback')) {
            errorDiv = document.createElement('div');
            errorDiv.className = 'invalid-feedback';
            field.parentNode.insertBefore(errorDiv, field.nextSibling);
        }
        errorDiv.textContent = message;
    }

    clearError(field) {
        field.classList.remove('is-invalid');
        const errorDiv = field.nextElementSibling;
        if (errorDiv && errorDiv.classList.contains('invalid-feedback')) {
            errorDiv.remove();
        }
    }
}

// ============================================
// 8. LOADING STATES
// ============================================
class LoadingManager {
    static show(elementId) {
        const element = document.getElementById(elementId);
        if (element) {
            element.innerHTML = '<div class="d-flex justify-content-center p-5"><div class="spinner"></div></div>';
        }
    }

    static hide(elementId, originalContent) {
        const element = document.getElementById(elementId);
        if (element) {
            element.innerHTML = originalContent;
        }
    }
}

// ============================================
// 9. CONFIRMATION DIALOGS
// ============================================
function confirmDelete(itemName, callback) {
    if (confirm(`Êtes-vous sûr de vouloir supprimer "${itemName}" ? Cette action est irréversible.`)) {
        callback();
    }
}

// ============================================
// 10. INITIALIZATION
// ============================================
document.addEventListener('DOMContentLoaded', () => {
    // Initialize sidebar
    const sidebar = new SidebarManager();
    
    // Initialize search
    const search = new SearchManager();
    
    // Initialize all forms with validation
    document.querySelectorAll('form[data-validate]').forEach(form => {
        new FormValidator(form.id);
    });
    
    // Initialize data tables
    document.querySelectorAll('table[data-table]').forEach(table => {
        new DataTable(table.id);
    });
    
    // Add fade-in animation to cards
    document.querySelectorAll('.card, .character-card').forEach((card, index) => {
        card.style.animationDelay = `${index * 0.05}s`;
        card.classList.add('animate-fade-in');
    });
    
    // Handle delete confirmations
    document.querySelectorAll('[data-delete]').forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            const itemName = button.dataset.delete;
            const url = button.dataset.url;
            
            confirmDelete(itemName, () => {
                fetch(url, { method: 'DELETE' })
                    .then(response => {
                        if (response.ok) {
                            toast.show('Supprimé avec succès', 'success');
                            button.closest('.card, tr').remove();
                        } else {
                            toast.show('Erreur lors de la suppression', 'error');
                        }
                    });
            });
        });
    });
    
    // Copy to clipboard functionality
    document.querySelectorAll('[data-copy]').forEach(button => {
        button.addEventListener('click', () => {
            const text = button.dataset.copy;
            navigator.clipboard.writeText(text).then(() => {
                toast.show('Copié dans le presse-papiers', 'success');
            });
        });
    });
    
    console.log('🚀 Heroes & Villains App initialized');
});

// Export for use in other scripts
window.App = {
    CONFIG,
    toast,
    debounce,
    formatDate,
    formatNumber,
    getInitials,
    LoadingManager,
    confirmDelete
};
