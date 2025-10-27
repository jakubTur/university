[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=19628317)
# Template for the lab assignment

## How to use this repository

1. After cloning this repository, create the virtual environment first:
   ```
   python -m venv .venv
   ```
1. Activate your environment, in a way appropriate to your environment.
1. When environment is active, install requirements:
   ``` 
   pip install -r requirements.txt
   ```
1. Run `app.py` from command line (Terminal in Linux/MacOS, CMD in Windows):
   ```
   python app.py
   ```
1. Follow the lab instruction.
1. Remember to commit each added feature or fix.

## Assignment - links to the information sources

- [Sample Link 1](https://pwr.edu.pl)
   '''
   pycodestyle initial output:

   app.py:6:1: E265 block comment should start with '# '
   app.py:7:1: E302 expected 2 blank lines, found 0
   app.py:13:5: E122 continuation line missing indentation or outdented
   app.py:14:5: E122 continuation line missing indentation or outdented
   app.py:15:5: E122 continuation line missing indentation or outdented
   app.py:16:5: E122 continuation line missing indentation or outdented
   app.py:17:5: E122 continuation line missing indentation or outdented
   app.py:18:5: E122 continuation line missing indentation or outdented
   app.py:46:80: E501 line too long (95 > 79 characters)
   app.py:55:34: E225 missing whitespace around operator
   app.py:57:1: E265 block comment should start with '# '
   app.py:58:1: E302 expected 2 blank lines, found 0
   app.py:64:1: E265 block comment should start with '# '
   app.py:65:1: E302 expected 2 blank lines, found 0
   app.py:66:80: E501 line too long (115 > 79 characters)
   app.py:69:54: E225 missing whitespace around operator
   app.py:80:1: E265 block comment should start with '# '
   app.py:81:1: E302 expected 2 blank lines, found 0
   app.py:88:1: E265 block comment should start with '# '
   app.py:95:1: E302 expected 2 blank lines, found 1
   app.py:102:19: E275 missing whitespace after keyword
   app.py:102:62: E225 missing whitespace around operator
   app.py:106:1: E265 block comment should start with '# '
   app.py:107:1: E302 expected 2 blank lines, found 0
   app.py:111:1: E265 block comment should start with '# '
   app.py:112:1: E302 expected 2 blank lines, found 0
   app.py:114:14: E225 missing whitespace around operator
   app.py:115:11: E275 missing whitespace after keyword
   app.py:116:19: E225 missing whitespace around operator
   app.py:117:15: E275 missing whitespace after keyword
   app.py:117:22: E225 missing whitespace around operator
   app.py:118:80: E501 line too long (90 > 79 characters)
   app.py:120:1: E302 expected 2 blank lines, found 1
   app.py:123:18: E225 missing whitespace around operator
   app.py:125:13: E225 missing whitespace around operator
   app.py:126:12: E225 missing whitespace around operator
   app.py:133:1: E305 expected 2 blank lines after class or function definition, found 1
   app.py:137:16: W292 no newline at end of file

   after changes:

   app.py:48:80: E501 line too long (83 > 79 characters)
   app.py:72:80: E501 line too long (115 > 79 characters)
   '''