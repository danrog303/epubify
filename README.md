# epubify
![CI](https://github.com/danrog303/epubify/actions/workflows/ci.yml/badge.svg)
![Activity](https://shields.io/github/last-commit/danrog303/epubify)
![Gitmoji](https://img.shields.io/badge/gitmoji-%20📝%20🏗️-FFDD67.svg)
> Java library for easy ebook creating.

## 📝 Project description
Epubify library makes it easy to programmatically generate documents in _epub_ format.
Epubify was inspired by my other project named [novels-dl](https://github.com/danrog303/novels-dl), for which I wrote a package that generates epub files.
Epubify takes some codebase from novels-dl, removing references to the light novel genre and optimizing some core consepts.

## ✨ Features
- Writing basic metadata (book name, author, description)
- Writing book cover (from file or from http(s) url)
- Writing images (from file or from http(s) url)
- Writing multiple chapters and generating table of contents
- Modular design, which makes the library easily expandable

## 🔧 How to use?
### 1️⃣ How to build the library?
Epubify uses Java + Gradle, so to build the library jar file, you just need to run `gradlew build` on the cloned repository. Compiled jar file will be present in the `build/libs/` directory.
```
$ git clone https://github.com/danrog303/epubify
$ cd epubify
$ ./gradlew build
```

### 2️⃣ Tests
Epubify project includes several automated tests to validate if the library is working correctly.
To run tests, just use:
```
$ ./gradlew test
```

### 3️⃣ Example library usage
The source code below will create an example 15 chapter ebook with an embedded cover image and with 15 images.
Images are specified as URLs, but you can use file paths as well.
```java
import java.util.EnumSet;
import com.github.danrog303.epubify.compiler.epub.EpubCompiler;
import com.github.danrog303.epubify.models.Ebook;
import com.github.danrog303.epubify.models.EbookChapter;
import com.github.danrog303.epubify.utils.CssUnit;
import com.github.danrog303.epubify.models.ParagraphSeparation;

var ebook = new Ebook();
ebook.setName("Ebook name");
ebook.setAuthor("Ebook Author");
ebook.setCoverImage("https://user-images.githubusercontent.com/32397526/181013763-0d60e56e-3c7d-4f99-a0fb-8006ddd64d39.jpg");

for(int i = 0; i < 15; i++) {
  ebook.addChapter("Chapter Name", "<p>Chapter HTML <img src='https://user-images.githubusercontent.com/32397526/181014221-b230b0f6-2c7e-49ab-b443-c757d76fec8c.jpg' alt='image'/></p>");
}

// You can separate <p> tags by using indents or margins
// This 3 lines enable indents, but default option is to use margins instead
var opts = new EbookOptions();
opts.setParagraphSeparationMethod(EnumSet.of(ParagraphSeparation.INDENTS));
opts.setParagraphIndentationSize(new CssUnit("0.5em"));

var compiler = new EpubCompiler(opts);
compiler.compile(ebook, "/home/daniel/out.epub");
```

## ☕ Java version
The library was developed on JDK 17, and its operation has not been tested on other versions.
