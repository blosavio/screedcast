
  <body>
    <a href="https://clojars.org/com.sagevisuals/screedcast"><img src="https://img.shields.io/clojars/v/com.sagevisuals/screedcast.svg"></a><br>
    <a href="#setup">Setup</a><br>
    <a href="https://blosavio.github.io/screedcast/index.html">API</a><br>
    <a href="https://github.com/blosavio/screedcast/blob/main/changelog.md">Changelog</a><br>
    <a href="#intro">Introduction</a><br>
    <a href="#usage">Usage</a><br>
    <a href="#examples">Examples</a><br>
    <a href="#alternatives">Alternatives</a><br>
    <a href="#glossary">Glossary</a><br>
    <a href="https://github.com/blosavio">Contact</a><br>
    <h1>
      Screedcast
    </h1><em>A highly un-configurable library for generating coding screencasts</em><br>
    <section id="setup">
      <h2>
        Setup
      </h2>
      <h3>
        Leiningen/Boot
      </h3>
      <pre><code>[com.sagevisuals/screedcast &quot;2-SNAPSHOT0&quot;]</code></pre>
      <h3>
        Clojure CLI/deps.edn
      </h3>
      <pre><code>com.sagevisuals/screedcast {:mvn/version &quot;2-SNAPSHOT0&quot;}</code></pre>
      <h3>
        Require
      </h3>
      <pre><code>(require &apos;[screedcast.core :refer [generate-all-screencasts]])</code></pre>
    </section>
    <section id="intro">
      <h2>
        Introduction
      </h2>
      <p>
        Software documentation ought to have <a href="https://github.com/blosavio/readmoi">lots of code examples</a>. Because some people learn better by
        watching audio-visual screencasts, those screencasts ought to have lots of code examples, too. Or, perhaps someone is considering a library, but would
        prefer to casually watch and listen to a screencast with a cup of tea instead of wading through pages and pages of dry text.
      </p>
      <p>
        A standard slideshow presentation does not display code examples in an audience-friendly manner. While they may offer animations for transitioning from
        one slide to the next, they don&apos;t have any particular awareness of text that represents code. Displaying a mass of code can be visually
        overwhelming, and it&apos;s difficult for people in the audience to focus on what the speaker is dicussing. One tactic is to progressively reveal
        examples to keep people&apos;s attention focused on the moment&apos;s topic. Typical slideshow software does not facilitate this.
      </p>
      <p>
        And standard slideshow presentation software does not make it straightforward include code examples. We must write the examples in the code editor,
        evaluate it, then copy-paste the entire glob into the slideshow. If we want to change a code example, we must delete it, and start the dance all over
        again.
      </p>
      <p>
        Screedcast is a library for generating a screencast with evaluated Clojure code examples. Except for the source file directories and the output file
        directories, there are almost no settings to be adjusted. Take it as it is.
      </p>
      <p>
        Screencasts are authored in <a href="https://github.com/weavejester/hiccup">hiccup</a>-dialect <span class="small-caps">html</span>. A utility function
        inserts a minuscule amount of javascript/<span class="small-caps">css</span> around Clojure code examples. When clicked/tapped, the screencast gently
        reveals the expression&apos;s value. Code examples are easily adjusted directly inside the document, the functions are always up-to-date with the
        library, and the screencast reveals the code examples in a manner that focuses the audience&apos;s attention.
      </p>
      <p></p>
    </section>
    <section id="usage">
      <h2>
        Usage
      </h2>
      <h3>
        Overview
      </h3>
      <p>
        We write a screencast, which is, at the bottom, Clojure code in <code>.clj</code> files. The code examples are straight Clojure, while the surrounding
        text, formatting, and document organization are hiccup forms. Then, we create an <em>options</em> file, that tells Screedcast where to find the files
        and where to send the output <span class="small-caps">html</span> files. Finally, we tell Screedcast to compile the hiccup forms into <span class=
        "small-caps">html</span>. Those <span class="small-caps">html</span> files are viewable in any modern web browser. When rendered by a web browser,
        clicking/tapping on a Clojure expression triggers a <span class="small-caps">dom</span> event that toggles the visibility of expression&apos;s
        evaluation.
      </p>
      <h3>
        Detailed usage
      </h3>
      <ol>
        <li>
          <p>
            Complete the <a href="#setup">setup</a>.
          </p>
        </li>
        <li>
          <p>
            Write the presentation in hiccup/<span class="small-caps">html</span>. Each episode section is formatted like this.
          </p>
          <pre><code>[:body
&nbsp; (panel <em>❬hiccup content❭</em>)
&nbsp; ...]</code></pre>
          <p>
            The <code>panel</code> function is convenience utility automatically creates the hiccup required for a header, footer, page-numbering, etc.
          </p>
          <p>
            When we wish to create a code example, we use the following pattern.
          </p>
          <pre><code>(prettyfy-form-prettyfy-eval &quot;(inc 99)&quot;)</code></pre>
          <p>
            Notice that we do not insert a <code>100</code>. <code>prettyfy-form-prettyfy-eval</code> does that for us during compilation. If, while writing
            the screencast, we&apos;d like to know what the expression evaluates to, put the cursor right before the closing quotation marks and
            <code>eval-last-sexp</code>.
          </p>
          <p>
            See also a <a href="https://github.com/blosavio/readmoi?tab=readme-ov-file#detailed-usage">sibling project&apos;s usage</a> for a few more tips.
          </p>
          <p>
            Screedcast also includes a trio of <span class="small-caps">css</span> helpers.
          </p>
          <ul>
            <li>
              <p>
                <code>[:div.vspace]</code> provides some vertical breathing room between elements.
              </p>
            </li>
            <li>
              <p>
                This pattern provides side-by-side column content blocks.
              </p>
              <pre><code>[:div.side-by-side-container
&nbsp; [:div.side-by-side
&nbsp;   [:p &quot;Stuff in the left column.&quot;]
&nbsp;   [:p &quot;Lorem ipsum dolor...&quot;]]
&nbsp; [:div.side-by-side
&nbsp;   [:p &quot;Stuff in the right column.&quot;]
&nbsp;   [:p &quot;Duis aute irure...&quot;]]]</code></pre>
            </li>
            <li>Speaker notes may be included within a <code>[:div.note ...]</code>. Their visibility is toggled by clicking the copyright notice.
            </li>
          </ul>
        </li>
        <li>
          <p>
            Insert the required and optional information in the <a href=
            "https://github.com/blosavio/screedcast/blob/main/resources/screedcast_options.edn"><code>screedcast_options.edn</code></a> file. Feel free to
            copy-paste <a href="https://github.com/blosavio/screedcast/blob/main/resources/screedcast_options.edn">this example</a>, and edit as necessary.
          </p>
          <p>
            The following keys are <strong>required</strong> (i.e., they do not have a default value):
          </p>
          <ul>
            <li>
              <p>
                <code>:screencast-filename-bases</code> A vector of hash-maps, one hash-map for each screencast. Each map contains the following keys:
              </p>
              <ul>
                <li>
                  <p>
                    <code>:screencast-filename</code> A string representing the filename without the <code>.clj</code> extension.
                  </p>
                </li>
                <li>
                  <p>
                    <code>:screencast-title</code> A string that will appear as the first text of the screencast title in the first panel.
                  </p>
                </li>
                <li>
                  <p>
                    <code>:screencast-uuid</code> A version 4 Universally Unique Identifier.
                  </p>
                </li>
              </ul>
            </li>
            <li>
              <p>
                <code>:project-name-formatted</code> A string containing the name of the project, appearing in the title panel.
              </p>
            </li>
            <li>
              <p>
                <code>:project-description</code> A string describing the project, appearing in the web browser&apos;s OS window frame.
              </p>
            </li>
            <li>
              <p>
                <code>:project-webpage-link</code> A string appearing in the footer of the screencast.
              </p>
            </li>
            <li>
              <p>
                <code>:copyright-holder</code> A string used to create the copyright notice.
              </p>
            </li>
          </ul>
          <p>
            The following keys are <strong>optional</strong>, and will revert to defaults if not supplied by the options.
          </p>
          <ul>
            <li>
              <p>
                <code>:project-license-section</code> A hiccup/<span class="small-caps">html</span> form that announces the screencast&apos;s license. Defaults
                to the MIT license.
              </p>
            </li>
            <li>
              <p>
                <code>:sections-directory</code> A string representing the directory to find the hiccup/<span class="small-caps">html</span> files. Include the
                trailing &apos;/&apos;. Defaults to &apos;resources/screencast_sections/&apos;.
              </p>
            </li>
            <li>
              <p>
                <code>:screencast-html-directory</code> A string representing the directory to place the output files. Include the trailing &apos;/&apos;.
                Defaults to &apos;doc/screencast_slides/&apos;.
              </p>
            </li>
            <li>
              <p>
                <code>:separator</code> A string representing a sequence of characters to insert between the Clojure code form and resulting evaluation.
                Defaults to &apos; =&gt; &apos;.
              </p>
            </li>
            <li>
              <p>
                <code>:wrap-at</code> An integer that governs the wrapping column for the <a href="https://github.com/kkinnear/zprint">zprint</a> pretty
                printer. Defaults to <code>80</code>.
              </p>
            </li>
            <li>
              <p>
                <code>:tidy-html?</code> Indent and wrap <span class="small-caps">html</span>. Defaults to <code>false</code>.
              </p>
            </li>
            <li>
              <p>
                <code>:toc?</code> A boolean that controls generating a <em>Table of Contents</em> page with hyperlinks to each screencast section. Defaults to
                <code>false</code>.
              </p>
            </li>
            <li>
              <p>
                <code>:toc-uuid</code> A UUID for the TOC webpage.
              </p>
            </li>
            <li>
              <p>
                <code>:toc-url-base</code> A string representing the leading portion of the url up to the slug where the screencasts are served. Include the
                trailing &apos;/&apos;. Defaults to <code>nil</code>. <em>Note:</em> this value does not point to the local file system, but intended to be
                somewhere like the project&apos;s GitHub resources sub-directory.
              </p>
            </li>
            <li>
              <p>
                <code>:toc-preamble</code> A hiccup/<span class="small-caps">html</span> form that will be displayed above the screencast listings.
              </p>
            </li>
          </ul>
        </li>
        <li>
          <p>
            Generate the <span class="small-caps">html</span> files. Screedcast is not fancy. Just evaluate this.
          </p>
          <pre><code>(generate-all-screencasts (load-file &quot;resources/screedcast_options.edn&quot;))</code></pre>
          <p>
            Or use a <a href="https://github.com/blosavio/screedcast/blob/main/resources/screedcast_generator.clj">generator script</a>. Find the <span class=
            "small-caps">html</span> files in the output directory, which defaults to <code>doc/screencast_slices/</code>.
          </p>
        </li>
      </ol>
    </section>
    <section id="examples">
      <h2>
        Examples
      </h2><a href="https://blosavio.github.io/screedcast/screencast_slides/table_of_contents.html">Table of Contents</a><br>
      <a href="https://blosavio.github.io/screedcast/screencast_slides/intro.html">Introduction screencast</a><br>
      <a href="https://blosavio.github.io/screedcast/screencast_slides/usage.html">Usage screencast</a><br>
      <a href="https://blosavio.github.io/screedcast/screencast_slides/create.html">Creating screencast</a>
    </section>
    <section id="alternatives">
      <h2>
        Alternatives
      </h2>
      <ul>
        <li>
          <p>
            Traditional slideshow applications, such as Apple <em>Keynote</em>, Google <em>Slides</em>, LibreOffice <em>Impress</em>, or Microsoft
            <em>PowerPoint</em>.
          </p>
        </li>
        <li>
          <p>
            LaTeX/Beamer
          </p>
        </li>
        <li>
          <p>
            Various Emacs modes
          </p>
        </li>
      </ul>
    </section>
    <section id="glossary">
      <h2>
        Glossary
      </h2>
      <dl>
        <dt id="panel">
          panel
        </dt>
        <dd>
          <p>
            A single frame of a screencast, written in hiccup-style <span class="small-caps">html</span>, which may contain Clojure code examples. Screedcast
            uses the term <em>panel</em> to distinguish between a traditional <a href="#slideshow">slideshow</a> <em>slide</em>.
          </p>
        </dd>
      </dl>
      <dl>
        <dt id="screed">
          screed
        </dt>
        <dd>
          <p>
            A lengthy, boring piece of discourse.
          </p>
        </dd>
      </dl>
      <dl>
        <dt id="screencast">
          screencast
        </dt>
        <dd>
          <p>
            An presentation whose visual component is a series of screens and whose audio component is voice narration.
          </p>
        </dd>
      </dl>
      <dl>
        <dt id="section">
          section
        </dt>
        <dd>
          <p>
            One <span class="small-caps">html</span> file that contains the <a href="#panel">panels</a> for one episode of a screencast. The Screedcast
            generator is capable of compiling multiple episodes with a single invocation.
          </p>
        </dd>
      </dl>
      <dl>
        <dt id="slideshow">
          slideshow
        </dt>
        <dd>
          <p>
            A typical presentation authored in Apple <em>Keynote</em>, Google <em>Slides</em>, LibreOffice <em>Impress</em>, or Microsoft <em>PowerPoint</em>,
            etc. No particular provisions for displaying evaluated Clojure code examples.
          </p>
        </dd>
      </dl>
    </section><br>
    <h2>
      License
    </h2>
    <p></p>
    <p>
      This program and the accompanying materials are made available under the terms of the <a href="https://opensource.org/license/MIT">MIT License</a>.
    </p>
    <p></p>
    <p id="page-footer">
      Copyright © 2024 Brad Losavio.<br>
      Compiled by <a href="https://github.com/blosavio/readmoi">ReadMoi</a> on 2024 December 10.<span id="uuid"><br>
      4ba73391-1867-4667-99b9-384ada88e0ab</span>
    </p>
  </body>
</html>
