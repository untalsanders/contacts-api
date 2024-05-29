up: down build-app
	@echo "💥 Turning on services..."
	@docker compose up --build

build-app: clean
	@echo "💥 Built App..."
	@./gradlew build --warning-mode all

run-tests:
	@./gradlew test --warning-mode all

down:
	@echo "💥 Turning off services..."
	@docker compose down

.PHONY: clean
clean:
	@echo "💥 Removing related JAR's..."
	@./gradlew clean --warning-mode all
