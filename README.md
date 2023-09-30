# 🔄 Casing

> Scala Library for Naming Convention Transitions (camelCase, PascalCase, snake_case, kebab-case, etc.)

[![Build Status](https://github.com/ranyitz/casing/actions/workflows/ci.yml/badge.svg)](https://github.com/ranyitz/casing/actions/workflows/ci.yml)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.ranyitz/casing_2.13.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.ranyitz%22%20AND%20a:%22casing_2.13%22)
[![Scala Version](https://img.shields.io/badge/scala-2.12%20%7C%202.13%20%7C%203.0-blue.svg)](https://www.scala-lang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Features
✂️ Split words in any kind of [Naming Convention](https://en.wikipedia.org/wiki/Naming_convention_(programming)) <br/>
🪶 Dependency free <br/>
🧪 Fully tested <br/>

## Installation
To include Casing in your Scala project, add the following dependency:

```scala
libraryDependencies += "io.github.ranyitz" %% "casing" % "0.2.0"
```

## Usage

```scala
import casing._

camelCase("foo_bar") // fooBar
pascalCase("foo bar") // FooBar
snakeCase("fooBar") // foo_bar
kebabCase("foo-bar") // foo-bar
constantCase("foo.bar") // FOO_BAR

caseSplit("fooBarBaz") // foo.bar.baz 
    .map(_.toLowerCase())
    .mkString(".") 
```

### split
splits a string into words based on the casing pattern

> can be used to create any custom naming pattern

```scala
caseSplit("fooBarBaz") // Seq(foo, Bar, Baz)
caseSplit("foo_bar_baz") // Seq(foo, bar, baz)
caseSplit("foo-bar-baz") // Seq(foo, bar, baz)
caseSplit("FOO_BAR_BAZ") // List(FOO, BAR, BAZ)
```

### camelCase
converts a string to [camelCase](https://en.wikipedia.org/wiki/Camel_case)

```scala
camelCase("foo_bar") // fooBar
```

### pascalCase
converts a string to [PascalCase](https://en.wikipedia.org/wiki/Camel_case)

```scala
pascalCase("foo_bar") // FooBar
```

### snakeCase
converts a string to [snake_case](https://en.wikipedia.org/wiki/Snake_case)

```scala
snakeCase("fooBar") // foo_bar
```

### kebabCase
converts a string to [kebab-case](https://en.wikipedia.org/wiki/Letter_case#Special_case_styles)

```scala
kebabCase("fooBar") // foo-bar
```

### constantCase
converts a string to CONSTANT_CASE or [SCREAMING_SNAKE_CASE](https://en.wikipedia.org/wiki/Snake_case)

```scala
constantCase("fooBar") // FOO_BAR
```

### Validation Functions
validates a string against a specific naming convention

```scala
isCamelCase("fooBar") // true
isPascalCase("FooBar") // true
isSnakeCase("foo_bar") // true
isKebabCase("foo-bar") // true
isConstantCase("FOO_BAR") // true
```

### Inspiration
* [change-case](https://github.com/blakeembrey/change-case)
