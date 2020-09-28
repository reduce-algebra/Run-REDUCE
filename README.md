# Run-REDUCE-FX

## A JavaFX GUI to run any CLI version of the REDUCE Computer Algebra System

### Francis Wright, September 2020

Run-REDUCE-FX is a re-implementation of
[Run-REDUCE](https://fjwright.github.io/Run-REDUCE/) using JavaFX
instead of Swing.  The latest version of Run-REDUCE-FX requires Java
11 or later plus JavaFX 11 or later.

For further general background information please see the
[Run-REDUCE-FX web page](https://fjwright.github.io/Run-REDUCE-FX/).
For information about how to install and run Run-REDUCE-FX please see
the [Install and Run
Guide](https://fjwright.github.io/Run-REDUCE-FX/InstallAndRun.html).
For information about how to use Run-REDUCE-FX please see the [User
Guide](https://fjwright.github.io/Run-REDUCE-FX/UserGuide.html) (which
is also included in Run-REDUCE-FX and easily accessible via the Help
menu).

Run-REDUCE-FX should run on any platform that supports JavaFX 11 (or
later), but I can only test it on Microsoft Windows and Linux.
(Whilst Java is portable, filesystem structures, installation
conventions and display systems are not!)

REDUCE itself is an open source project available from
[SourceForge](https://sourceforge.net/projects/reduce-algebra/).  I'm
releasing Run-REDUCE-FX under the [BSD 2-Clause License](LICENSE),
mainly because it's the license used by REDUCE.

This project is set up for development using [IntelliJ
IDEA](https://www.jetbrains.com/idea/) with Run-REDUCE-FX as the
top-level directory.

Run-REDUCE-FX currently uses a bundled copy of
[KaTeX](https://katex.org/) to render LaTeX output by the REDUCE
*fmprint* package when the *Typeset Maths* option is selected.

## Release Notes

### Version 1.1

* Avoid warning messages and hanging Help menu items on Ubuntu, but
  suppress display of PDF files via the Help menu on non-Windows
  platforms since it's probably not currently very helpful!
* Add instructions for installing and running using Java 11 on Linux
  and improve the appearance of the User Guide.
* Use CSS to set the REDUCE font, size, weight and colour, and use the
  same font and size for input as well as output.
* Validate direct input to the FontSizeDialog via the editor.
* Validate generic root directories in REDUCEConfigDialog.  Rebuild
  the Run REDUCE submenus on saving REDUCEConfigDialog.  Improve error
  messages.

### Version 1.2

* Update the build environment to Java 11 and JavaFX 11, which are now
  also required to run Run-REDUCE-FX.
* Provide batch files to run Run-REDUCE-FX more easily.
* Re-instate display of PDF files via the Help menu on non-Windows
  platforms.
* Fix truncated text in the About dialogue.

### Version 1.3

* Provide templates for *df*, *int* and *mat* expressions and *for*
  statements.
* Add a hyperlinked Contents section to the User Guide.

### Version 1.4

* Provide templates for multiple integrals, finite sums and products,
  double- and single-sided limits, and solve.
* Provide a Functions menu offering dialogues that facilitate access
  to key elementary functions, Gamma, Beta and related functions,
  integral functions, Airy, Bessel and related functions, Struve,
  Lommel, Kummer, Whittaker and spherical harmonic functions, and
  classical orthogonal polynomials.  Special function names link to
  the online NIST Digital Library of Mathematical Functions.
* Appropriate templates include a symbolic/numeric option and access
  to relevant REDUCE switches and packages.
* Provide a pop-up keyboard (currently only on template and function
  dialogues) for symbolic constants, Greek letters, elementary
  functions, trigonometric functions (using radians or degrees) and
  hyperbolic functions.  **Still need to arrange that the pop-up
  keyboard loads the trigd package as appropriate.**
* The function dialogues and pop-up keyboard together offer all the
  functions listed in section 7.2 Mathematical Functions of the REDUCE
  Manual plus degree versions of all trigonometric functions.

### Version 1.5

* Add REDUCE Manual hyperlinks to all template and function dialogues.
* Add Help menu items that open the REDUCE Web Site and SourceForge
  Project Site in the default browser.

### Version 1.6

* Use WebView to display REDUCE output, and update instructions and
  batch files for running Run-REDUCE-FX accordingly.
* Set the default Session Log filename to `session.log`.
* Turning bold prompts on and off now works retrospectively.
* Turning I/O colouring on and off now works retrospectively (as far
  as possible).

### Version 1.7

* Add experimental Typeset I/O option, currently hidden, to the View
  menu to use fmprint and KaTeX, which does not yet work reliably.
* Update the documentation to try to make it easier to access the
  latest jar.
* Redesign the REDUCE Configuration dialogue:
  - Replace Packages Root Directory with REDUCE Packages Directory.
  - Split Documentation Root Dir into REDUCE Manual Directory and
    REDUCE Primers Directory.
  - **The above two changes are not backwards compatible!**
  - Add Initial I/O Directory, which defaults to the user's home
    directory, as before.
  - Add a ContextMenu to implement key choices for Initial I/O
    Directory: Home Directory; Current Directory; Another Directory.

### Version 1.8

* The Typeset Maths View menu option enables typeset-style display of
  algebraic-mode output.  But beware that this is currently
  experimental and has a number of limitations.
* Rename Initial I/O Directory to REDUCE Working Directory in the
  REDUCE Configuration dialogue.  This now sets the initial directory
  for both REDUCE and the file selectors and defaults to the user's
  home directory.
* View options apply to each REDUCE panel independently, initialised
  from the last selected (and saved) values, and the redfront View
  option takes effect after the next prompt.
* A toggle button on each REDUCE panel hides the input editor.
* When split-pane view is enabled after startup, the new panel is
  active and a green dot at the top right shows which panel is
  selected.

### Version 1.9

* Detect question prompts correctly.
* Add a *Print Session Log...* item to the File menu, which prints the
  entire content of the I/O Display pane as it appears on screen.
* Fix the *Save Session Log...* code to output the TeX markup used to
  display typeset maths.
* Move *Load Packages...* to the *REDUCE* menu.
* Support the pop-up keyboard on the input editor and add a label
  "Control+Click for the Pop-Up Keyboard" with a tooltip to the top of
  the input editor and the bottom of each template dialogue.
* Handle more of the non-standard TeX markup generated by fmprint.

### Version 2.0

* Fix the display of big delimiters in typeset maths.
* Always insert parentheses when calling a function using the pop-up
  keyboard, rewrite ln to log and remove the space after sqrt.

### Updates since last release

* v2.01 Add -debugMenu program parameter to enable a Debugging menu.
  Improve handling of program parameters.  Move "Save Raw Session
  Log..." menu item from File menu to new Debugging menu and add new
  "Show TeX Markup" menu item to optionally display TeX markup before
  typeset output.
* v2.02 Improve handling of \symb{n} and include 34 -> \forall and 36
  -> \exists.
* Replace loading the pre-compiled fmprint package with inputting the
  new source file "rrprint.red" contained in the JAR file, which is
  based on "tmprint.red".
* Stop using katexMacros and some \symb mappings, which are no longer
  required.  Match up \left and \right in REDUCEPanel.
* Output identifiers in mathit instead of mathrm style.
* Fix spacing of typeset maths assignment (:=).
* Display a trailing multiplication sign in typeset maths but
  otherwise ignore multiplications.  (This may be too drastic.)
* Fix spacing of leading + or -.
* Compile "rrprint.red" on input.
* Rationalise typesetting of elementary transcendental functions.
* v2.03 Support typesetting all trigonometric and hyperbolic functions
  and their inverses.
* Display the gamma function, but not the identifier gamma, using a
  capital Gamma.  Add typeset maths support for polygamma, iGamma,
  iBeta, dilog, Pochhammer and the Integral Functions.
* Apply a fix by Eberhard Shruefer: Reform sq's in tmprint and fmprint
  so that an order command has an effect, e.g. order x,t; f(x-t); now
  prints as expected, namely f(x-t) rather than f(-t+x).
* Decode all Greek letters from the pop-up keyboard and from templates
  to LaTeX names.
* Add typeset maths support for Airy and Hankel functions.
* Add typeset maths support for Struve, Lommel, Kummer, Whittaker and
  Spherical Harmonic Functions, and Classical Orthogonal Polynomials.
* Correct Greek/Unicode spelling lamda/Lamda to TeX spelling
  lambda/Lambda in pop-up keyboard.
* Map Greek letter names to KaTeX control words and avoid Symbol font
  code points; handle sech and csch, which are not defined in KaTeX.
* Handle logb and log10.
