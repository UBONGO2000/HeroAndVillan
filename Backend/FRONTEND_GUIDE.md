# 🎨 Guide du Frontend - Heroes & Villains

Ce guide explique l'architecture frontend du projet, les bonnes pratiques utilisées et comment maintenir le code.

---

## 📁 Structure des Fichiers

```
src/main/resources/
├── static/                    # Fichiers statiques servis par Spring Boot
│   ├── css/
│   │   └── main.css          # Feuille de styles principale
│   └── js/
│       └── app.js            # JavaScript principal
│
└── templates/                 # Templates Thymeleaf
    ├── base.html             # Template de base (layout)
    ├── index.html            # Page d'accueil
    ├── heroes.html           # Liste des héros
    ├── villains.html         # Liste des vilains
    ├── anti-heroes.html      # Liste des anti-héros
    ├── hero.html             # Détail d'un héros
    ├── villain.html          # Détail d'un vilain
    └── ...
```

---

## 🏗️ Architecture

### Séparation des Responsabilités

```
┌─────────────────────────────────────────────────────────────┐
│                      TEMPLATE (HTML)                         │
│  • Structure sémantique                                      │
│  • Contenu dynamique (Thymeleaf)                            │
│  • Aucun style inline (sauf exceptions)                     │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                      CSS (main.css)                          │
│  • Design tokens (variables CSS)                            │
│  • Composants réutilisables                                 │
│  • Layout et responsive                                     │
│  • Animations                                               │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                    JAVASCRIPT (app.js)                       │
│  • Interactivité                                            │
│  • Appels API                                               │
│  • Gestion d'état                                           │
│  • Utilitaires                                              │
└─────────────────────────────────────────────────────────────┘
```

---

## 🎨 Système de Design

### 1. Variables CSS (Design Tokens)

Les variables CSS sont définies dans `:root` et permettent une cohérence visuelle :

```css
:root {
    /* Couleurs */
    --primary-500: #6366f1;
    --primary-600: #4f46e5;
    --hero-color: #3b82f6;
    --villain-color: #ef4444;
    --antihero-color: #8b5cf6;
    
    /* Typographie */
    --font-sans: 'Inter', sans-serif;
    --text-base: 1rem;
    --text-lg: 1.125rem;
    
    /* Espacements */
    --space-4: 1rem;
    --space-6: 1.5rem;
    
    /* Ombres */
    --shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    --shadow-lg: 0 10px 15px rgba(0, 0, 0, 0.1);
}
```

### 2. Composants Réutilisables

#### Cartes de Personnages
```html
<div class="character-card hero">
    <div class="character-image">
        <i class="fas fa-mask"></i>
    </div>
    <div class="character-content">
        <span class="character-type hero">Héros</span>
        <h3 class="character-name">Nom</h3>
        <p class="character-description">Description</p>
    </div>
</div>
```

#### Cartes de Statistiques
```html
<div class="stat-card">
    <div class="stat-icon blue">
        <i class="fas fa-mask"></i>
    </div>
    <div class="stat-info">
        <h3>42</h3>
        <p>Héros</p>
    </div>
</div>
```

### 3. Couleurs par Type de Personnage

| Type | Couleur | Classe CSS | Icône |
|------|---------|------------|-------|
| Héros | Bleu (#3b82f6) | `.hero` | `fa-mask` |
| Vilain | Rouge (#ef4444) | `.villain` | `fa-skull` |
| Anti-Héros | Violet (#8b5cf6) | `.antihero` | `fa-user-secret` |

---

## 🧩 Système de Layout (base.html)

### Utilisation du Layout Thymeleaf

```html
<!DOCTYPE html>
<html lang="fr" xmlns:th="http://www.thymeleaf.org" 
      th:replace="~{base :: layout(
          pageTitle = 'Titre de la page',
          currentPage = 'heroes',           <!-- Pour activer le menu -->
          content = ~{::content},           <!-- Contenu principal -->
          pageStyles = ~{::styles},         <!-- Styles spécifiques -->
          pageScripts = ~{::scripts}        <!-- Scripts spécifiques -->
      )}">
<head>
    <th:block th:fragment="styles">
        <style>
            /* Styles spécifiques à cette page */
        </style>
    </th:block>
</head>
<body>
    <th:block th:fragment="content">
        <!-- Contenu de la page -->
    </th:block>
    
    <th:block th:fragment="scripts">
        <script>
            // Scripts spécifiques à cette page
        </script>
    </th:block>
</body>
</html>
```

---

## ⚡ JavaScript - Architecture Modulaire

### Classes Principales

#### 1. ToastManager
Gestion des notifications :
```javascript
// Afficher un toast
toast.show('Message de succès', 'success');
toast.show('Erreur', 'error');
toast.show('Attention', 'warning');
```

#### 2. SidebarManager
Gestion de la sidebar responsive :
```javascript
const sidebar = new SidebarManager();
sidebar.toggle();  // Ouvrir/fermer
sidebar.open();    // Ouvrir
sidebar.close();   // Fermer
```

#### 3. SearchManager
Recherche en temps réel avec debounce :
```javascript
const search = new SearchManager();
// Automatiquement attaché à #search-input
```

#### 4. FormValidator
Validation des formulaires :
```javascript
new FormValidator('myFormId');
```

### Utilitaires Globaux

```javascript
// Debounce
const debouncedFn = debounce(() => console.log('Appelé'), 300);

// Formatage
toast.show(formatDate('2024-01-15'));  // "15 janvier 2024"
formatNumber(1000000);                  // "1 000 000"
getInitials('Bruce Wayne');             // "BW"

// Confirmation
confirmDelete('Batman', () => {
    // Action de suppression
});
```

---

## 📱 Responsive Design

### Breakpoints

| Breakpoint | Largeur | Classe CSS |
|------------|---------|------------|
| Mobile | < 768px | `d-none d-md-block` |
| Tablette | 768px - 1024px | `d-md-none` |
| Desktop | > 1024px | Défaut |

### Patterns Responsive

```css
/* Mobile-first approach */
.characters-grid {
    grid-template-columns: 1fr;  /* Mobile */
}

@media (min-width: 768px) {
    .characters-grid {
        grid-template-columns: repeat(2, 1fr);  /* Tablette */
    }
}

@media (min-width: 1024px) {
    .characters-grid {
        grid-template-columns: repeat(4, 1fr);  /* Desktop */
    }
}
```

---

## 🎯 Bonnes Pratiques

### HTML / Thymeleaf

✅ **À FAIRE :**
- Utiliser des balises sémantiques (`<section>`, `<article>`, `<nav>`)
- Ajouter des attributs `th:if` pour gérer les états vides
- Utiliser `${} ?: 'valeur par défaut'` pour les valeurs nullables
- Spécifier `th:fragment` pour les composants réutilisables

❌ **À ÉVITER :**
- Styles inline (`style="color: red"`)
- JavaScript inline (`onclick="..."`)
- Tables pour le layout

### CSS

✅ **À FAIRE :**
- Utiliser les variables CSS pour les couleurs/espacements
- Organiser par sections (Layout, Components, Utilities)
- Commenter les sections complexes
- Utiliser BEM pour le naming (optionnel ici)

❌ **À ÉVITER :**
- `!important` sauf cas exceptionnels
- Sélecteurs trop profonds (`.a .b .c .d`)
- Duplication de code

### JavaScript

✅ **À FAIRE :**
- Utiliser ES6+ (const, let, arrow functions, classes)
- Débouncer les événements fréquents (input, scroll, resize)
- Gérer les erreurs avec try/catch
- Commenter les fonctions complexes

❌ **À ÉVITER :**
- Variables globales
- `var` (utiliser `let` ou `const`)
- Callbacks imbriquées (utiliser async/await)

---

## 🚀 Ajouter une Nouvelle Page

### 1. Créer le Template

```html
<!DOCTYPE html>
<html lang="fr" xmlns:th="http://www.thymeleaf.org" 
      th:replace="~{base :: layout(
          pageTitle = 'Ma Page - Heroes & Villains',
          currentPage = 'mapage',
          content = ~{::content}
      )}">
<body>
    <th:block th:fragment="content">
        <h1>Contenu de ma page</h1>
    </th:block>
</body>
</html>
```

### 2. Ajouter au Menu (base.html)

```html
<div class="nav-section">
    <div class="nav-section-title">Section</div>
    <a href="/mapage" class="nav-link" th:classappend="${currentPage == 'mapage'} ? 'active'">
        <i class="fas fa-icon"></i>
        <span>Ma Page</span>
    </a>
</div>
```

### 3. Créer le Controller Spring Boot

```java
@Controller
public class MaPageController {
    
    @GetMapping("/mapage")
    public String maPage(Model model) {
        model.addAttribute("currentPage", "mapage");
        // ... autres attributs
        return "mapage";
    }
}
```

---

## 🧪 Tests Frontend

### Tester le Responsive

```bash
# Utiliser les DevTools du navigateur
# Device Toolbar > Responsive
# Tester : 320px, 768px, 1024px, 1440px
```

### Vérifier les Performances

```bash
# Lighthouse (Chrome DevTools)
# Objectifs :
# - Performance > 90
# - Accessibilité > 90
# - SEO > 90
```

---

## 📚 Ressources

- **Inter Font**: https://fonts.google.com/specimen/Inter
- **Font Awesome**: https://fontawesome.com/icons
- **Thymeleaf**: https://www.thymeleaf.org/documentation.html
- **CSS Variables**: https://developer.mozilla.org/fr/docs/Web/CSS/--*
- **Grid Layout**: https://css-tricks.com/snippets/css/complete-guide-grid/

---

## 🔄 Mise à Jour du Design

Pour modifier le design :

1. **Couleurs** : Modifier les variables CSS dans `:root`
2. **Typographie** : Changer la police Google Fonts et `--font-sans`
3. **Espacements** : Ajuster les variables `--space-*`
4. **Composants** : Modifier les classes dans les sections appropriées

---

**Guide maintenu par l'équipe Heroes & Villains 🦸‍♂️🦹‍♂️**
