package eu.matejkormuth.jge.filesystem;

import javax.annotation.Nonnull;

public interface FileSystem {

    Resource load(@Nonnull String path);

}
