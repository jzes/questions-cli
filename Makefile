install:
	@echo "installing"
	@lein uberjar
	@mv target/default+uberjar/questions-cli-0.1.0-SNAPSHOT-standalone.jar quest-cli.jar
	@chmod +x quest-cli