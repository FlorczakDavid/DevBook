# DevbookPresentation

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 19.2.6.

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Karma](https://karma-runner.github.io) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.

## Environment set up
In your local, create a file named "environment.development.ts" in src/environments folder. 
In this file, you can configure the variables like below.
```
export const environment = {
  apiUrl: 'http://localhost:8080/'
};

```

### Project Structure (initial)

```sh
src/
│
├── app/
│   ├── core/              # Services globaux (auth, interceptor, API, etc.)
│   │   ├── services/     
│   │   ├── guards/        # Route guards
│   │   └── interceptors/  # Intercepteurs HTTP
│   │
│   ├── shared/            # Composants & pipes réutilisables
│   │   ├── directives/    # Directives personnalisées
│   │   ├── pipes/         # Pipes personnalisés
│   │   └── pipes/
│   │       └── truncate.pipe.ts
│   │
│   ├── features/          # Vos modules métier (domaines fonctionnels)
│   │   ├── providers-rss/
│   │   │   ├── providers-rss.component.ts
│   │   │   ├── providers-rss.service.ts
│   │   │   └── providers-rss.css
│   │   ├── items/
│   │   │   ├── items.component.ts
│   │   │   ├── items.service.ts
│   │   │   └── items.css
│   │   └── ...
│   ├── layouts/            # Layouts de l’application (Header, Footer, Sidebar)
│   ├── app-routing.module.ts
│   └── app.module.ts
│
├── assets/
│   └── imgs/              # Fichiers de traduction si besoin
│
├── environments/
│   ├── environment.ts
│   └── environment.prod.ts
│
└── styles.scss            # Styles globaux
```
