PACKAGE=com.evroon.kotlin_soundboard
GRADLE=./gradlew
ADB=adb

.PHONY: build app

clean:
	$(GRADLE) clean

test:
	$(GRADLE) clean lint test

build:
	$(GRADLE) installDebug

app: build
	$(ADB) shell monkey -p $(PACKAGE) -c android.intent.category.LAUNCHER 1

docker-test:
	docker-compose run --rm test

docker-build:
	docker-compose build test
