## Locators

### Basic Locators

* ID
```java
driver.findElement(By.id("id"));
```

* Name
```java
driver.findElement(By.name("name"));
```

* Class Name
```java
driver.findElement(By.className("className"));
```

* Tag Name
```java
driver.findElement(By.tagName("div"));
```

* Link Text
```java
driver.findElement(By.linkText("Sign In"));
```

* Partial Link Text
```java
driver.findElement(By.partialLinkText("ign I"));
```

### Advanced Locators
* CSS Selector
```java
driver.findElement(By.cssSelector("[data-test=submit]"));
```

* XPath Selector
```java
driver.findElement(By.xpath("//*[@data-test='submit']"));
```

### CSS Selector Combinations

* Multiple Classes
```java
driver.findElement(By.cssSelector(".classone.classtwo"));
```

* Multiple Attributes
```java
driver.findElement(By.cssSelector("[data-attributeone='one][data-attributetwo='two']"));
```

### CSS Selector Hierarchy
* Any Descendant
```java
driver.findElement(By.cssSelector(".parent .grandchild"));
```

* Direct Descendant
```java
driver.findElement(By.cssSelector(".parent > .child"));
```
___
```java
WebElement table = driver.findElement(By.id("table"));
```


* First Child
```java
table.findElement(By.cssSelector("tr:first-child"));
```

* Last Child
```java
table.findElement(By.cssSelector("tr:last-child"));
```

* Third Child
```java
table.findElement(By.cssSelector("tr:nth-child(3)"));
```

* Third Child From End
```java
table.findElement(By.cssSelector("tr:nth-last-child"));
```

### CSS Selector Siblings
* Following Sibling
```java
driver.findElement(By.cssSelector(".first ~ .third"));
```

* Next Sibling
```java
driver.findElement(By.cssSelector(".first + .second"));
```

### CSS Selector Substrings

* Starts With
```java
driver.findElement(By.cssSelector("[data-test^=begin]"));
```

* Ends With
```java
driver.findElement(By.cssSelector("[data-test$=inning]"));
```

* Contains
```java
driver.findElement(By.cssSelector("[data-test*=gin]"));
```
