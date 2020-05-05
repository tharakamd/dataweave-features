# Insert Attributes to an XML Tag

## Description



This DataWeave example uses @(key:value) syntax (for example, @(loc: "US")) to inject attributes to XML tags. The example populates the title element with two attributes and each of the author elements with one attribute

#### Input
``` xml
<bookstore>
  <book>
    <title>Everyday Italian</title>
    <year>2005</year>
    <price>30</price>
    <author>Giada De Laurentiis</author>
  </book>
  <book>
    <title>Harry Potter</title>
    <year>2005</year>
    <price>29.99</price>
    <author>J K. Rowling</author>
  </book>
  <book>
    <title>XQuery Kick Start</title>
    <year>2003</year>
    <price>49.99</price>
    <author>James McGovern</author>
    <author>Per Bothner</author>
    <author>Kurt Cagle</author>
    <author>James Linn</author>
    <author>Vaidyanathan Nagarajan</author>
  </book>
  <book>
    <title>Learning XML</title>
    <year>2003</year>
    <price>39.95</price>
    <author>Erik T. Ray</author>
  </book>
</bookstore>
```
#### Output

``` xml
<bookstore>
  <book>
    <title lang="en" year="2005">Everyday Italian</title>
    <price>30</price>
    <author loc="US">Giada De Laurentiis</author>
  </book>
  <book>
    <title lang="en" year="2005">Harry Potter</title>
    <price>29.99</price>
    <author loc="US">J K. Rowling</author>
  </book>
  <book>
    <title lang="en" year="2003">XQuery Kick Start</title>
    <price>49.99</price>
    <author loc="US">James McGovern</author>
    <author loc="US">Per Bothner</author>
    <author loc="US">Kurt Cagle</author>
    <author loc="US">James Linn</author>
    <author loc="US">Vaidyanathan Nagarajan</author>
  </book>
  <book>
    <title lang="en" year="2003">Learning XML</title>
    <price>39.95</price>
    <author loc="US">Erik T. Ray</author>
  </book>
</bookstore>
```

#### Dataweave Script

```
%dw 2.0
output application/xml
---
bookstore: myInput.bookstore mapObject (book) -> {
      book : {
      title @(lang: "en", year: book.year): book.title,
      price: book.price,
      (book.*author map
      author @(loc: "US"): $)
    }
}
```

#### Groovy Script
