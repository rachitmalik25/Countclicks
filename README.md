# Countclicks

A small Java web app for demonstrating a GitHub Actions CI/CD pipeline. Click the button to increment a counter held by the Spring Boot application.

## Run locally

Requirements: Java 17 or newer and Maven.

```sh
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080). The counter API is available at `GET /api/clicks` and `POST /api/clicks`; `GET /api/health` reports the app status.

## Run tests

```sh
mvn verify
```

## GitHub Actions CI/CD

The workflow in `.github/workflows/ci-cd.yml` has separate unit-test and build jobs, followed by a SonarQube placeholder job. It runs for pushes, pull requests targeting `main`, and manual runs. SonarQube is not configured; the placeholder indicates where to add a scanner and credentials later.

On pushes to `main`, the workflow builds and publishes a Docker image to GitHub Container Registry (GHCR), tagged `latest` and with the commit SHA. It then runs development, staging, and production deployment jobs in sequence. Each deployment job is a placeholder that must be replaced with your hosting provider's deploy command and credentials; the stages use GitHub Environments named `development`, `staging`, and `production`. Configure environment protection rules in repository settings if you want approvals before staging or production.

The publish job uses the workflow's `GITHUB_TOKEN`; ensure the repository's Actions settings allow the workflow to write packages. After the first successful publish, the image is available at `ghcr.io/<owner>/countclicks` and can be run with:

```sh
docker run --rm -p 8080:8080 ghcr.io/<owner>/countclicks:latest
```

Pull requests and manual runs perform CI only; publishing and deployment occur only for pushes to `main`.
