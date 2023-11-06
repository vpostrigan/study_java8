<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" omit-xml-declaration="yes" doctype-public="-//IETF//DTD HTML//EN//2.0"/>

    <!-- specify the root of the XML document -->
    <!-- that references this stylesheet      -->
    <xsl:template match="/">
        <html>
            <!-- info.html               -->
            <!-- i-Mode information page -->

            <head>
                <title>Tip Test</title>
            </head>

            <body>
                <p>
                    <!-- display image -->
                    <img name="image" height="40" width="40" alt="Tip Image">
                        <xsl:attribute name="src">images/<xsl:value-of select="tipImageName"/>.gif
                        </xsl:attribute>
                    </img>
                    <br/>

                    What is the name of the icon shown?
                    <br/>

                    <!-- create a form with four checkboxes -->
                    <form method="post" action="imode/checkAnswer.asp">

                        <!-- build a table for the options -->
                        <table>
                            <tr>
                                <td>
                                    <input type="radio" name="radioButton">
                                        <xsl:attribute name="value">
                                            <xsl:value-of select="selection"/>
                                        </xsl:attribute>
                                    </input>
                                    <xsl:value-of select="selection"/>
                                </td>
                                <td>
                                    <input type="radio" name="radioButton">
                                        <xsl:attribute name="value">
                                            <xsl:value-of select="selection"/>
                                        </xsl:attribute>
                                    </input>
                                    <xsl:value-of select="selection"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" name="radioButton">
                                        <xsl:attribute name="value">
                                            <xsl:value-of select="selection"/>
                                        </xsl:attribute>
                                    </input>
                                    <xsl:value-of select="selection"/>
                                </td>
                                <td>
                                    <input type="radio" name="radioButton">
                                        <xsl:attribute name="value">
                                            <xsl:value-of select="selection"/>
                                        </xsl:attribute>
                                    </input>
                                    <xsl:value-of select="selection"/>
                                </td>
                            </tr>
                        </table>

                        <input type="submit" value="Submit"/>

                        <input type="hidden" name="correct">
                            <xsl:attribute name="value">
                                <xsl:value-of select="correctAnswer"/>
                            </xsl:attribute>
                        </input>

                        <input type="hidden" name="descriptionField">
                            <xsl:attribute name="value">
                                <xsl:value-of select="dummydescription"/>
                            </xsl:attribute>
                        </input>
                    </form>
                </p>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
