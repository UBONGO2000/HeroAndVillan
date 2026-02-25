# Contributing to CMMS Backend

Thank you for your interest in contributing to the CMMS Backend project! This document provides guidelines and standards for contributing.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Workflow](#development-workflow)
- [Coding Standards](#coding-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)
- [Testing Guidelines](#testing-guidelines)

## Code of Conduct

By participating in this project, you agree to maintain a respectful and inclusive environment for all contributors.

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.9+
- PostgreSQL 15+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Setup

1. Fork the repository
2. Clone your fork locally
3. Create a feature branch
4. Make your changes
5. Submit a pull request

## Development Workflow

### Branch Naming Convention

| Type | Pattern | Example |
|------|---------|---------|
| Feature | `feature/description` | `feature/add-authentication` |
| Bugfix | `fix/description` | `fix/null-pointer-exception` |
| Hotfix | `hotfix/description` | `hotfix/security-patch` |
| Release | `release/version` | `release/1.2.0` |

### Workflow Steps

```bash
# 1. Create a new branch
git checkout -b feature/your-feature-name

# 2. Make changes and commit
git add .
git commit -m "feat: add new feature"

# 3. Push to your fork
git push origin feature/your-feature-name

# 4. Create a Pull Request
```

## Coding Standards

### Java Code Style

#### Naming Conventions

| Element | Convention | Example |
|---------|------------|---------|
| Classes | PascalCase | `VillainService` |
| Interfaces | PascalCase | `VillainRepository` |
| Methods | camelCase | `findVillainById()` |
| Variables | camelCase | `villainList` |
| Constants | UPPER_SNAKE_CASE | `MAX_PAGE_SIZE` |
| Packages | lowercase | `com.springbootlearning.backend` |

#### Class Structure

```java
/**
 * Service class for managing Villain entities.
 * 
 * @author Your Name
 * @version 1.0.0
 */
@Service
public class VillainService {

    // 1. Constants
    private static final Logger logger = LoggerFactory.getLogger(VillainService.class);
    
    // 2. Dependencies (final fields)
    private final VillainRepository villainRepository;
    
    // 3. Constructor
    public VillainService(VillainRepository villainRepository) {
        this.villainRepository = villainRepository;
    }
    
    // 4. Public methods
    public Villain findVillainById(UUID id) {
        // Implementation
    }
    
    // 5. Private methods
    private void validateVillain(Villain villain) {
        // Implementation
    }
}
```

#### Documentation Standards

**Class-level Javadoc:**

```java
/**
 * Service class for managing Villain entities.
 * 
 * <p>This service provides CRUD operations for villains,
 * including validation and error handling.</p>
 * 
 * @author Your Name
 * @version 1.0.0
 * @since 1.0.0
 * @see Villain
 * @see VillainRepository
 */
```

**Method-level Javadoc:**

```java
/**
 * Finds a villain by its unique identifier.
 *
 * @param id the UUID of the villain to find
 * @return the found Villain entity
 * @throws NotFoundException if no villain exists with the given ID
 */
public Villain findVillainById(UUID id) {
    // Implementation
}
```

### REST Controller Standards

```java
@RestController
@RequestMapping("/api/v1/villains")
public class VillainController {

    private final VillainService villainService;

    public VillainController(VillainService villainService) {
        this.villainService = villainService;
    }

    /**
     * Gets all villains.
     *
     * @return list of all villains
     */
    @GetMapping
    public ResponseEntity<Iterable<Villain>> getAllVillains() {
        return ResponseEntity.ok(villainService.findAllVillains());
    }

    /**
     * Creates a new villain.
     *
     * @param villainDto the villain data
     * @return the created villain with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Villain> createVillain(@Valid @RequestBody VillainDto villainDto) {
        Villain created = villainService.createVillain(villainDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
```

### Service Layer Standards

```java
@Service
public class VillainService {

    private static final Logger logger = LoggerFactory.getLogger(VillainService.class);
    private final VillainRepository villainRepository;

    public VillainService(VillainRepository villainRepository) {
        this.villainRepository = villainRepository;
    }

    @Transactional(readOnly = true)
    public Villain findVillainById(UUID id) {
        return villainRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Villain not found with id: " + id));
    }

    @Transactional
    public Villain createVillain(VillainDto villainDto) {
        Villain villain = mapToEntity(villainDto);
        Villain saved = villainRepository.save(villain);
        logger.info("Created villain with id: {}", saved.getId());
        return saved;
    }
}
```

## Commit Guidelines

### Commit Message Format

Follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

| Type | Description |
|------|-------------|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation changes |
| `style` | Code style changes (formatting, etc.) |
| `refactor` | Code refactoring |
| `test` | Adding or modifying tests |
| `chore` | Build process or auxiliary tool changes |
| `perf` | Performance improvements |

### Examples

```bash
# Feature
feat(controller): add VillainController with CRUD endpoints

# Bug fix
fix(service): handle null pointer in findVillainById

# Documentation
docs(readme): update installation instructions

# Refactoring
refactor(service): extract validation logic to separate method

# Test
test(service): add unit tests for VillainService
```

## Pull Request Process

### Before Submitting

1. **Run tests**: Ensure all tests pass
   ```bash
   ./mvnw test
   ```

2. **Check code style**: Follow the coding standards

3. **Update documentation**: Update README.md if needed

4. **Add tests**: Cover new functionality with tests

### PR Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] All tests pass

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] No new warnings introduced
```

### Review Process

1. At least one approval required
2. All CI checks must pass
3. No merge conflicts
4. Squash and merge to main

## Testing Guidelines

### Unit Tests

```java
@ExtendWith(MockitoExtension.class)
class VillainServiceTest {

    @Mock
    private VillainRepository villainRepository;

    @InjectMocks
    private VillainService villainService;

    @Test
    @DisplayName("Should return villain when found by ID")
    void findVillainById_ShouldReturnVillain_WhenExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Villain expected = new Villain();
        expected.setId(id);
        when(villainRepository.findById(id)).thenReturn(Optional.of(expected));

        // Act
        Villain result = villainService.findVillainById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(villainRepository, times(1)).findById(id);
    }
}
```

### Test Naming Convention

Use the `MethodName_StateUnderTest_ExpectedBehavior` pattern:

```java
@Test
void findVillainById_WhenVillainExists_ShouldReturnVillain() { }

@Test
void findVillainById_WhenVillainNotFound_ShouldThrowNotFoundException() { }

@Test
void createVillain_WithValidData_ShouldReturnCreatedVillain() { }
```

### Test Coverage

- Aim for at least 80% code coverage
- Cover both happy path and error scenarios
- Use `@DisplayName` for readable test names

---

Thank you for contributing! 🎉
