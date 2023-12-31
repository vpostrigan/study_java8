<?xml version="1.0"?>

<!-- WAPTipQuestion.xsl -->
<!-- WAP stylesheet     -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="xml" omit-xml-declaration="no"
                doctype-system="http://www.wapforum.org/DTD/wml_1.1.xml"
                doctype-public="-//WAPFORUM//DTD WML 1.1//EN"/>

    <!-- specify the root of the XML document -->
    <!-- that references this stylesheet      -->
    <xsl:template match="question">

        <wml>
            <card id="card1" title="Tip Test">
                <do type="accept" label="OK">
                    <go href="#card2"/>
                </do>
                <p>
                    <img src="{image}" height="55" width="55"
                         alt="Tip Image"/>
                </p>
            </card>

            <card id="card2" title="Tip Test">
                <do type="accept" label="Submit">
                    <go method="post" href="/advjhtp1/tiptest">
                        <postfield name="userAnswer" value="$(question)"/>
                    </go>
                </do>
                <p>The tip shown on the previous screen is called:
                </p>
                <p>
                    <select name="question" iname="iquestion" ivalue="1">
                        <option value="0">
                            <xsl:value-of select="choices/choice[1]"/>
                        </option>
                        <option value="1">
                            <xsl:value-of select="choices/choice[2]"/>
                        </option>
                        <option value="2">
                            <xsl:value-of select="choices/choice[3]"/>
                        </option>
                        <option value="3">
                            <xsl:value-of select="choices/choice[4]"/>
                        </option>
                    </select>
                </p>
            </card>
        </wml>
    </xsl:template>
</xsl:stylesheet>
