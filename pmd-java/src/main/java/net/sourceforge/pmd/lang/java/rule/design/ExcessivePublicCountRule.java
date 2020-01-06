/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.rule.design;

import java.util.Objects;

import net.sourceforge.pmd.lang.java.ast.ASTAnnotationTypeDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTAnyTypeBodyDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTAnyTypeDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTEnumDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTFieldDeclaration;
import net.sourceforge.pmd.lang.java.ast.AccessNode;
import net.sourceforge.pmd.lang.java.rule.internal.AbstractJavaCounterCheckRule;

/**
 * Rule attempts to count all public methods and public attributes
 * defined in a class.
 *
 * <p>If a class has a high number of public operations, it might be wise
 * to consider whether it would be appropriate to divide it into
 * subclasses.</p>
 *
 * <p>A large proportion of public members and operations means the class
 * has high potential to be affected by external classes. Futhermore,
 * increased effort will be required to thoroughly test the class.
 * </p>
 *
 * @author aglover
 */
public class ExcessivePublicCountRule extends AbstractJavaCounterCheckRule<ASTAnyTypeDeclaration> {

    public ExcessivePublicCountRule() {
        super(ASTAnyTypeDeclaration.class,
              ASTEnumDeclaration.class,
              ASTClassOrInterfaceDeclaration.class,
              ASTAnnotationTypeDeclaration.class);
    }

    @Override
    protected int defaultReportLevel() {
        return 45;
    }

    @Override
    protected boolean isViolation(ASTAnyTypeDeclaration node, int reportLevel) {
        long publicCount = node.getDeclarations().stream()
                               .map(ASTAnyTypeBodyDeclaration::getDeclarationNode)
                               .filter(Objects::nonNull)
                               .filter(it -> it instanceof AccessNode && ((AccessNode) it).isPublic())
                               // filter out constants
                               .filter(it -> !(it instanceof ASTFieldDeclaration && ((ASTFieldDeclaration) it).isFinal() && ((ASTFieldDeclaration) it).isStatic()))
                               .count();

        return publicCount >= reportLevel;
    }
}