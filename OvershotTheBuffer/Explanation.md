# OvershotTheBuffer
Completed on 26th October 2020

## Explanation
This is a classic example of a buffer overflow attack using the *gets* function in C. In this scenario, we have a fake login prompt to some system, which prompts for the password. 

This password is stored in a 16-character array serving as the 'buffer' - as the comments in the program show, *gets* does not check the size of the buffer, so if you input more than 16 characters, it will continually to sequentially write characters in each byte of memory.

This has the potential to overwrite the value of an Integer flag which keeps track of whether the user has 'logged in'; if this is overwritten, it will be non-zero, allowing the code containing within an if-statement to run. 

In a real scenario, such as Heartbleed, not checking the size of your buffer could lead to genuine problems!