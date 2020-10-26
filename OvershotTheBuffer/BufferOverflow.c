#include <stdio.h>
#include <string.h>

int main(void)
{
    // We store an array of 16 characters, which is the length of our true password.
    char buff[16];
    // We keep track of whether we're 'logged in', as it were.
    int loggedIn = 0;

    // Now, we prompt for the password:
    printf("Enter the super-secret password: ");
    // gets doesn't know how big the buffer is, so it will accept and store more than 16 characters.
    // This has the potential to overwrite the value of loggedIn as it will continue sequentially writing characters
    //  past the end of the buffer.
    gets(buff);

    // Recall, that in C, zero equals false and non-zero equals true.
    // strcmp returns 0 if the two strings are identical; else, it returns some non-zero number.
    if(strcmp(buff, "16letterpassword"))
    {
        printf ("Wrong Password\n");
    }
    else
    {
        printf ("Correct Password\n");
        loggedIn = 1;
    }

    // If we're 'logged in', we welcome the user.
    // Except, if we wrote too much into the buffer from earlier, we might've overwritten this value to be non-zero
    //  when it wasn't supposed to be, giving access to our system unintentionally!
    if(loggedIn)
    {
        printf ("Welcome to the System");
    }

    // This just ensures the program stays open for the user to see the text.
    getchar();
    return 0;
}
