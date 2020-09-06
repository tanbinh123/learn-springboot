<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>书名</td>
        <td>价格</td>
        <td>---</td>
        <td>+++</td>
    </tr>
    <#list books as book>
        <#if book.price == 108>
            <#break>
        </#if>
        <tr>
            <td>${book.name}</td>
            <td>${book.price}</td>
            <td>
                <#if book.price == 101>
                    xxx
                <#elseif (book.price > 102 && book.price < 106)>
                    yyy
                <#else>
                    zzz
                </#if>
            </td>
            <td>
                <#switch book.price>
                    <#case 101>xxx<#break>
                    <#case 106>yyy<#break>
                    <#default>zzz
                </#switch>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>