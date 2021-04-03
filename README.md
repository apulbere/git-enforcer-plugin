# About
maven plugin which validates that commit messages matches the given pattern

# Example
include the following in your `pom.xml`:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>io.github.apulbere</groupId>
            <artifactId>git-enforcer-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
            <executions>
                <execution>
                    <phase>validate</phase>
                    <goals>
                        <goal>validate</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <commitMessagePattern>^(DE[0-9]+:|US[0-9]+|Merge).+</commitMessagePattern>
            </configuration>
        </plugin>
    </plugins>
</build>

<pluginRepositories>
    <pluginRepository>
        <id>sonatype-snapshot</id>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
    </pluginRepository>
    <pluginRepository>
        <id>sonatype-release</id>
        <url>https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/</url>
    </pluginRepository>
</pluginRepositories>
```
`mvn clean install` will fail if it doesn't match provided pattern.
Examples based on above config:

*Successful builds*
```
DE253: Fix NPE on app startup
Merge PR#23 to master
```

*Failed builds*
```
Fix NPE on app startup
```