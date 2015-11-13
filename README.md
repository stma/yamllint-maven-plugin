# yamllint-maven-plugin
it's purpose to lint all yaml file of a project during the validation phase

## For usage
Just add to your pom:
```xml
<plugin>
    <groupId>com.github.stma</groupId>
    <artifactId>yamllint-maven-plugin</artifactId>
    <version>@version.you.pleased@</version>
    <executions>
      <execution>
        <id>yamllint</id>
        <phase>validate</phase>
        <goals>
          <goal>yamllint</goal>
        </goals>
      </execution>
    </executions>
</plugin>
```

run maven:
`mvn validate`

## For development
### Integration test run
Run `install`, `integration-test` or `verify` goal to get the result.
`mvn verify -P run-its`

## Misc.
### Retrieve public key for check signing
`gpg2 --keyserver hkp://pool.sks-keyservers.net --recv-keys 99919F33`
