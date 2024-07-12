/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.sourceforge.pmd.lang.java.BaseJavaTreeDumpTest;
import net.sourceforge.pmd.lang.java.JavaParsingHelper;
import net.sourceforge.pmd.lang.test.ast.BaseParsingHelper;

class Java23TreeDumpTest extends BaseJavaTreeDumpTest {
    private final JavaParsingHelper java23 =
            JavaParsingHelper.DEFAULT.withDefaultVersion("23")
                    .withResourceContext(Java21TreeDumpTest.class, "jdkversiontests/java23/");

    @Override
    public BaseParsingHelper<?, ?> getParser() {
        return java23;
    }

    @Test
    void jep467MarkdownDocumentationComments() {
        doTest("Jep467_MarkdownDocumentationComments");
        ASTCompilationUnit unit = java23.parseResource("Jep467_MarkdownDocumentationComments.java");
        List<JavaComment> comments = unit.getComments();
        // first comment is the license header
        assertFalse(comments.get(0).hasJavadocContent());
        assertFalse(comments.get(0).isSingleLine());

        for (JavaComment c : comments.subList(1, comments.size())) {
            assertTrue(c.isSingleLine());
            assertTrue(c.getText().startsWith("///"));
            // System.out.println("found comment at " + c.getReportLocation() + ": " + c.getText());
            // TODO assertTrue(c.hasJavadocContent());
            // TODO assertInstanceOf(JavadocComment.class, c);
        }
    }
}
