<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Periodicals</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Title</th>
                        <th>Type Name</th>
                        <th>is glossy</th>
                        <th>monthly</th>
                        <th>color</th>
                        <th>pages</th>
                        <th>index</th>
                    </tr>
                    <xsl:for-each select="paper/periodical">
                        <tr>
                            <td>
                                <xsl:value-of select="title"/>
                            </td>
                            <td>
                                <xsl:for-each select="type">
                                    <xsl:value-of select="name"/>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:for-each select="type">
                                    <xsl:value-of select="glossy"/>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:value-of select="monthly"/>
                            </td>
                            <td>
                                <xsl:value-of select="color"/>
                            </td>
                            <td>
                                <xsl:value-of select="pages"/>
                            </td>
                            <td>
                                <xsl:value-of select="index"/>
                            </td>
                        </tr>
                        <xsl:sort select="pages"/>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>