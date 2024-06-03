package java.annotations;

import java.lang.annotation.Documented;

@Documented // Documented - To make the information in @ClassPreamble appear in Javadoc-generated documentation
@interface ClassPreamble {
    String author();

    String date();

    int currentRevision() default 1;

    String lastModified() default "N/A";

    String lastModifiedBy() default "N/A";

    // Note use of array
    String[] reviewers();
}