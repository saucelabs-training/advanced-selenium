# Address Book Tests in JUnit

This open source code is for use in introductory Selenium workshops.

See [LICENSE](https://github.com/saucelabs-training/advanced-selenium/LICENSE.md) file for details.

<br />

## Description

Different workshops and lessons will be managed by different branches. To ensure you are getting
the correct code for your workshop, go to the [Releases Page](https://github.com/saucelabs-training/advanced-selenium/releases 
)
and download the tutorial zip file that corresponds with your workshop.

<br />

## Workshop Prerequisites

1. [Install Git](https://github.com/saucelabs-training/advanced-selenium#install-git)
    * [Mac](https://github.com/saucelabs-training/advanced-selenium#macosx-screen-cast)
    * [Windows](https://github.com/saucelabs-training/advanced-selenium#windows-screen-cast)
2. [Install IntelliJ](https://github.com/saucelabs-training/advanced-selenium#install-intellij)
    * [Mac](https://github.com/saucelabs-training/advanced-selenium#macosx-screen-cast-1)
    * [Windows](https://github.com/saucelabs-training/advanced-selenium#windows-screen-cast-1)
3. [Install JDK](https://github.com/saucelabs-training/advanced-selenium#install-the-java-developers-kit)
    * [Mac](https://github.com/saucelabs-training/advanced-selenium#macosx-screen-cast-2)
    * [Windows](https://github.com/saucelabs-training/advanced-selenium#windows-screen-cast-2)
4. [Set up Project](https://github.com/saucelabs-training/advanced-selenium#set-up-this-project-in-intellij)
    * [Mac](https://github.com/saucelabs-training/advanced-selenium#macosx)
    * [Windows](https://github.com/saucelabs-training/advanced-selenium#windows)

### Install Git

[Git](https://git-scm.com/doc) is a version control system that lets you check out code from a repository, 
work with that code on your own branch, and then merge that code with any changes that have been made by other developers. 
Git is an essential tool for distributed development teams, and is a critical component of the continuous 
integration/continuous development toolchain.

#### MacOSX ([screen cast](https://youtu.be/N0cNxpbWUXs)):

1. Go to [https://git-scm.com/downloads](https://git-scm.com/downloads).
2. Under **Downloads**, click **Mac OS X**.
3. When the download completes, double-click the `.dmg` file open the installer package.
4. Double-click the installer package to begin the installation.
    > *Security Warning*
    >
    > You may see a warning message that the package can't be opened because it's not from a recognized developer. 
    If this happens, go to System Preferences > Security and Privacy Settings, and click Open Anyway.
5. Click **Continue** for the installation, and enter your local password to authorize the installation.

#### Windows ([screen cast](https://youtu.be/rQX7-Xataa0)):

1. Go to [https://git-scm.com/downloads](https://git-scm.com/downloads)
2. Under **Downloads**, click on **Windows**.
3. When the dialog opens asking if you want to allow the app to make changes to your device, click Yes.
4. Follow the steps in the setup wizard to complete the installation. You should accept all the default settings.
<br />

### Install IntelliJ

[IntelliJ](https://www.jetbrains.com/idea/) is an integrated development environment that incorporates several tools for developing and running Java code. You will be using IntelliJ to write and edit the sample Selenium scripts used in the exercises.  For these exercises you only need to download the free Community edition.

#### MacOSX ([screen cast](https://youtu.be/XyVyk5u2eac)):

1. Go to [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)
2. Click **Download**.
3. On the **Downloads** page, select **macOS**.
4. Under **Community**, click **Download**.
5. When the download completes, double-click the .dmg file open the installer package.
6. Double-click the installer package to begin the installation.
7. Drag and drop the IntelliJ icon into the **Applications** folder.

#### Windows ([screen cast](https://youtu.be/8ysBHlJN57g)):

1. Go to [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)
2. Click **Download**.
3. On the **Downloads** page, select **Windows**.
4. Under **Community**, click **Download**.
5. When the download completes, double-click the `.exe` file to launch the installation wizard. 
You should accept all the default settings.
<br />

### Install the Java Developer's Kit

The [Java SE Developer Kit](http://www.oracle.com/technetwork/java/javase/overview/index.html) lets you develop and 
deploy Java applications on desktops and servers. It is needed to compile our test code.

#### MacOSX ([screen cast](https://youtu.be/QIPv7oiWZR4)):

1. Go to [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Under **Java SE Development Kit 8u161**, select **Accept License Agreement**.
3. Click the download link for **Mac OS**.
4. When the download completes, double-click the `.dmg` file open the installer package.
Double-click the installer package to begin the installation.

#### Windows ([screen cast](https://youtu.be/5Vww6KUq5Mk)):

1. Go to [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Under **Java SE Development Kit 8u161**, select **Accept License Agreement**.
3. Click the download link for **Windows x64**.
4. When the download completes, double-click the `.exe` file open the installer package.
5. Double-click the installer package to begin the installation. You should accept all the default settings.
<br />

### Set Up This Project in IntelliJ

#### MacOSX:

1. Downloading the Project Files
    * Go to [https://github.com/saucelabs-training/advanced-selenium/releases](https://github.com/saucelabs-training/advanced-selenium/releases).
    * Download and extract the latest `tutorial_mac.zip` file, or the one that matches the name of your workshop.
        > You can save the zip file to any directory you want, including Downloads. You will just need to remember the 
        directory when you want to open the tutorial files in IntelliJ. 

2. Opening the Project in IntelliJ
    * Launch IntelliJ.
    * Click **Open**.
    * Browse to the **junit_tests** directory, and click **Open**.
    * Click the **Sidebars** icon in the lower left corner of IntelliJ to open the sidebars.
    * Open the **Project** sidebar and expand the directories **lib > src > test**.
    * In the **test/examples** directory, double click **SeleniumScript**, and a sample file will load into the editor window.

3. Configure the Project SDK

     > When the file loads, you'll see a notification at the top of the file that the SDK for the project is not defined.
    * Click **Setup SDK**, and then **Configure**.
    * Click **+** in the **Configure SDK** dialog.
    * Select **Java SDK**.
    * Browse to the directory where you installed the Java SDK and click **OK**. IntelliJ will load all the `.jar` files for the SDK. 
    * Click **OK** when the installation completes.

4. Confirm that Selenium is Running
    * In the file list on the left, right-click on the **SeleniumScript** file.
    * Select **Run 'SeleniumScript'**.
        >You should see the code begin to build, and then a console window will open. You should see that the driver opens and 
        closes a browser, confirming that the Selenium environment is running on your local machine.

5. Ensure Git is properly configured
    * Click the terminal tab on the bottom left of IntelliJ
    * Type the following commands into the terminal, replacing the example information with your own
        ```
        $ git config --global user.name "John Doe"
        $ git config --global user.email johndoe@example.com
        ```

### Windows:

1. Downloading the Project Files
    * Go to [https://github.com/saucelabs-training/advanced-selenium/releases](https://github.com/saucelabs-training/advanced-selenium/releases).
    * Download and extract the `tutorial_windows.zip` file, or the one that matches the name of your workshop.
        > You can save the zip file to any directory you want, including Downloads. You will just need to remember the 
        directory when you want to open the tutorial files in IntelliJ.

2. Opening the Project in IntelliJ
    * Launch IntelliJ.
    * Click **Open**.
    * Browse to the **projects > junit_tests** directory you created, and click **OK**.
    * Click the **Sidebars** icon in the lower left corner of IntelliJ to open the sidebars.
    * Open the **Project** sidebar.
    * In the Project sidebar, expand the directories **junit_tests > lib > src > test > examples**.
    * Select **SeleniumScript**, and a sample file will load into the editor window.

3. Configure the Project SDK

    When the file loads, you'll see a notification that the SDK for the project is not defined.
    * Click **Setup SDK**, and then **Configure**.
    * Click + in the **Configure SDK** dialog.
    * Select **Java SDK**.
    * Browse to the directory where you installed the Java SDK and click **OK**. IntelliJ will load all the `.jar` files for the SDK. 
    * Click **OK** when the installation completes.

4. Confirm that Selenium is Running
    * In the file list on the left, right-click on the **SeleniumScript** file.
    * Select **Run 'SeleniumScript'**.
        > You should see the code begin to build, and then a console window will open. You should see that the driver opens and 
        closes a browser, confirming that the Selenium environment is running on your local machine.

5. Ensure Git is properly configured
    * Click the terminal tab on the bottom left of IntelliJ
    * Type the following commands into the terminal, replacing the example information with your own
        ```
        $ git config --global user.name "John Doe"
        $ git config --global user.email johndoe@example.com
        ```
