package io.github.apulbere.gep;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.inject.Inject;
import java.util.regex.Pattern;

/**
 * Mojo which validates last commit on current branch
 */
@Mojo(name = "validate")
public class ValidateLatestCommitMojo extends AbstractMojo {

    @Inject
    private GitService gitService;

    @Parameter(property = "commitMessagePattern")
    private String commitMessagePattern;

    public void execute() throws MojoExecutionException {
        Pattern pattern = Pattern.compile(commitMessagePattern);
        String lastCommit = gitService.findLatestCommitMsgOnCurrentBranch();

        if(pattern.matcher(lastCommit).matches()) {
            getLog().info("maven-git-enforce successfully validated commit [" + lastCommit + "]");
        } else {
            throw new MojoExecutionException("commit message [" + lastCommit + "] failed validation");
        }
    }
}
