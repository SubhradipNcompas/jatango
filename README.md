# jatango

<plugin>
	<groupId>tech.grasshopper</groupId>
	<artifactId>cucumber-pdf-plugin</artifactId>
	<version>1.14.0</version>
	<executions>
		<execution>
			<id>report</id>
			<phase>post-integration-test</phase>
			<goals>
				<goal>pdfreport</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<cucumberJsonReportDirectory>${project.build.directory}/json-report</cucumberJsonReportDirectory>
	</configuration>
</plugin>