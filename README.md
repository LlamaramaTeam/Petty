# A fabric example mod

Feel free to use this template as you wish.

# Custom Language Adapter

The LanguageAdapter I've provided works only with objects.
    I mainly designed it for my own use.  

If you don't want to use it, remove `laguage_adapter/*` and edit the `fabric.mod.json` as follows:

```diff
- "languageAdapters": {
-   "custom": "com.username.modid.language_adapter.CustomAdapter"
- },
  "entrypoints": {
    "main": [
      {
-       "adapter": "custom",
+       "adapter": "kotlin",
        "value": "com.username.modid.MainFile"
      }
    ],
    "client": [
      {
-       "adapter": "custom",
+       "adapter": "kotlin",
        "value": "com.username.modid.MainFileClient"
      }
    ]
  }

```

Be sure to also update the `build.gradle.kts` file as follows:

````diff
- // dependency: kotlin(a,b) | String
- fun DependencyHandlerScope.includeModApi(dependency: Any) {
-   modApi(dependency)
-   include(dependency)
- }

-   // Kotlin Language Adapter (not needed, but if you prefer use it)
-   //modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")
+   modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")
-   // Own Language Adapter (Entry classes must be an object!)
-   includeModApi(kotlin("stdlib", kotlinVersion))
-   includeModApi(kotlin("stdlib-jdk8", kotlinVersion))
-   includeModApi(kotlin("stdlib-jdk7", kotlinVersion))
-   includeModApi(kotlin("reflect", kotlinVersion))
-   includeModApi("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
-   includeModApi("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.2")
-   includeModApi("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.5.2")

````

Be sure to add `fabric-language-kotlin": ">=1.6.5+kotlin.1.5.31` as a dependency in `fabric.mod.json`:

````diff
  "depends": {
    "fabricloader": ">=0.11.7",
    "fabric": "*",
    "minecraft": "1.17.x",
    "java": ">=16"
+   "fabric-language-kotlin": ">=1.6.5+kotlin.1.5.31",
  }

````