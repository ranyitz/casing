# 🔄 Casing

> Scala Library for Naming Convention Transitions (camelCase, PascalCase, snake_case, kebab-case, and more)

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
libraryDependencies += "io.github.ranyitz" %% "casing" % "0.1.0"
```

## Usage

```scala
import casing._

Casing.camelCase("foo_bar") // fooBar
Casing.pascalCase("foo bar") // FooBar
Casing.snakeCase("fooBar") // foo_bar
Casing.kebabCase("foo-bar") // foo-bar
Casing.constantCase("foo.bar") // FOO_BAR

Casing // foo.bar.baz 
    .split("fooBarBaz")
    .map(_.toLowerCase())
    .mkString(".") 
```

### split
splits a string into words based on the casing pattern

> can be used to create any custom naming pattern

```scala
Casing.split("fooBarBaz") // List(foo, Bar, Baz)
Casing.split("foo_bar_baz") // List(foo, bar, baz)
Casing.split("foo-bar-baz") // List(foo, bar, baz)
Casing.split("FOO_BAR_BAZ") // List(FOO, BAR, BAZ)
```

### camelCase
converts a string to [camelCase](https://en.wikipedia.org/wiki/Camel_case)

```scala
Casing.camelCase("foo_bar") // fooBar
```

### pascalCase
converts a string to [PascalCase](https://en.wikipedia.org/wiki/Camel_case)

```scala
Casing.pascalCase("foo_bar") // FooBar
```

### snakeCase
converts a string to [snake_case](https://en.wikipedia.org/wiki/Snake_case)

```scala
Casing.snakeCase("fooBar") // foo_bar
```

### kebabCase
converts a string to [kebab-case](https://en.wikipedia.org/wiki/Letter_case#Special_case_styles)

```scala
Casing.kebabCase("fooBar") // foo-bar
```

### constantCase
converts a string to CONSTANT_CASE or [SCREAMING_SNAKE_CASE](https://en.wikipedia.org/wiki/Snake_case)

```scala
Casing.constantCase("fooBar") // FOO_BAR
```

### Validation Functions
validates a string against a specific naming convention

```scala
Casing.isCamelCase("fooBar") // true
Casing.isPascalCase("FooBar") // true
Casing.isSnakeCase("foo_bar") // true
Casing.isKebabCase("foo-bar") // true
Casing.isConstantCase("FOO_BAR") // true
```

### Inspiration
* [change-case](https://github.com/blakeembrey/change-case)
