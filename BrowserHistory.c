#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_URL_LEN 256
#define STACK_SIZE 100

// Stack Structure
typedef struct {
    char items[STACK_SIZE][MAX_URL_LEN];
    int top;
} Stack;

// Stack Functions
void push(Stack *s, const char *url) {
    if (s->top < STACK_SIZE -1) {
        s->top++;
        strcpy(s->items[s->top], url);
    }
}

char* pop(Stack *s) {
    if(s->top >= 0) {
        return s->items[s->top--];
    }
    return NULL;
}

char* peek(Stack *s) {
    if(s->top >= 0) {
        return s->items[s->top];
    }
    return NULL;
}

int isEmpty(Stack *s) {
    return s->top == -1;
}

void clear(Stack *s) {
    s->top = -1;
}

// Browser History struct
typedef struct {
    Stack backStack;
    Stack forwardStack;
    char currentPage[MAX_URL_LEN];
} BrowserHistory;

// Initalize 
void initBrowser(BrowserHistory *b, const char *homepage) {
    b->backStack.top = -1;
    b->forwardStack.top = -1;
    strcpy(b->currentPage, homepage);
}

// Visit function
void visit(BrowserHistory *b, const char *url) {
    push(&b->backStack, b->currentPage);
    strcpy(b->currentPage, url);
    clear(&b->forwardStack);
    printf("Visited: %s\n", b->currentPage);
}

// Back function
void back(BrowserHistory *b, int steps) {
    int available = b->backStack.top + 1; // how many pages in back stack

    if( steps <= 0 || steps > available) {
        printf("Cannot go back %d steps.", steps);
        return;
    }

    char *url;
    for(int i=0; i< steps; i++) {
        if(!isEmpty(&b->backStack)) {
            push(&b->forwardStack, b->currentPage);
            url = pop(&b->backStack);
            strcpy(b->currentPage, url);
        }
    }
    printf("Went back to: %s\n", b->currentPage);
}

// Forward function
void forward(BrowserHistory *b, int Steps) {
    
    int available = b->forwardStack.top + 1; // how many pages in forward stack

    if (Steps <= 0 || Steps > available) {
        printf("Cannot go forward %d steps.", Steps);
        return;
    }

    char *url;
    for(int i=0; i < Steps; i++) {
        if(!isEmpty(&b->forwardStack)) {
            push(&b->backStack, b->currentPage);
            url = pop(&b->forwardStack);
            strcpy(b->currentPage, url);
        }
    }
    printf("Went forward to: %s\n", b->currentPage);
}

// Show History Function
void showHistory(BrowserHistory *b) {
    printf("\n======== Browser Stack View ========\n");

    Stack *back = &b->backStack;
    Stack *forward = &b->forwardStack;

    // backward stack
    for(int i = 0; i <= back->top; i++) {
        int index = -(back->top - i + 1);
        printf("%d. %s\n", index, back->items[i]);
    }

    // current page
    printf("0. %s  <-- current page\n", b->currentPage);
    
    // forward stack
    for(int i = forward->top; i >= 0; i--) {
        int index = (forward->top - i) + 1;
        printf("%d. %s\n", index, forward->items[i]);
    }
    printf("====================================\n");
}

// Main
int main(void) {
    BrowserHistory browser;
    initBrowser(&browser, "google.com");

    int choice;
    char url[MAX_URL_LEN];

    while (1) {
        printf("\n=== Browser ===\n");
        printf("Current Page: %s\n", browser.currentPage);
        printf("1. Visit New Page\n");
        printf("2. Back\n");
        printf("3. Forward\n");
        printf("4. Show History\n");
        printf("5. Exit\n");
        printf("Choose Option: ");
        scanf("%d", &choice);
        getchar(); // clear newline

        switch (choice) {
            case 1: {
                printf("Enter url: ");
                fgets(url, MAX_URL_LEN, stdin);
                url[strcspn(url, "\n")] = 0; // remove newline
                visit(&browser, url);
                break;
            }
            case 2: {
                int steps;
                printf("Enter Steps: ");
                scanf("%d", &steps);
                back(&browser, steps);
                break;
            }
            case 3: {
                int steps;
                printf("Enter Steps: ");
                scanf("%d", &steps);
                forward(&browser, steps);
                break;
            }
            case 4: {
                showHistory(&browser);
                break;
            }

            case 5: {
                printf("Exiting Browser....\n");
                return 0;
            }

            default: {
                printf("Invalid option.\n");
            }
        }
    }

    return 0;
}


