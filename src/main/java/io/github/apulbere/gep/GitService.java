package io.github.apulbere.gep;

import org.apache.maven.plugin.MojoExecutionException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import javax.inject.Named;
import java.io.File;

/**
 * GitService is in charge of interactions with Git
 */
@Named
public class GitService {

    private final FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();

    /**
     * Fetches last commit on current branch in current directory
     * @return commit short message (first line)
     * @throws MojoExecutionException when an issue was encountered while executing git commands
     */
    public String findLatestCommitMsgOnCurrentBranch() throws MojoExecutionException {
        try {
            File gitRepoDir = new File(System.getProperty("user.dir") + "/.git");
            Repository repository = repositoryBuilder.setGitDir(gitRepoDir)
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();
            RevCommit latestCommit = new Git(repository).log().setMaxCount(1).call().iterator().next();
            return latestCommit.getShortMessage();
        } catch (Exception e) {
            throw new MojoExecutionException("failed to retrieve commit", e);
        }
    }
}
