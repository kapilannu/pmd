/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.rule;

import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.apex.ApexLanguageModule;
import net.sourceforge.pmd.lang.apex.ApexParserOptions;
import net.sourceforge.pmd.lang.rule.XPathRule;

/**
 * @deprecated Will be removed with PMD 7. The only reason for this class were the code climate properties,
 *             which are already deprecated.
 */
@Deprecated
public class ApexXPathRule extends XPathRule {

    public ApexXPathRule() {
        super.setLanguage(LanguageRegistry.getLanguage(ApexLanguageModule.NAME));
    }

    @Override
    public ParserOptions getParserOptions() {
        return new ApexParserOptions();
    }
}
