/*=========================================================================
 * dec2base.c
 *
 * Author: Julien Phillips
 * Student ID: 260804197
 *
 * This program takes an input from the command line, those inputs being a number in base 10,
 * and the base the user wants to convert it to. If a base is not defined, the program assumes
 * the user wants the number to be converted to binary.
 *
 =========================================================================*/
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {

	int dec; // Decimal Number Input d=dec
	int base; // Base Number Input base=base
	int quot; // clone_d
	char result[100];
	int i = 0;
	char convert[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	int j = 0;


	if (argc != 2 && argc != 3) {
		printf("wrong number of arguments\n");
		return(0);
		}


	if (argc == 2){
		sscanf(argv[1],"%d",&dec); //taking input of decimal, but not base since it defaults to 2 when only decimal is inputed
		base = 2;
		quot = dec; //will only =dec on the first run of the while loop
		while (quot != 0) {
			result[i] = convert[(quot % base)]; // stores remainder to array
			quot = (quot/base); // Divides quotient by base
			i = i + 1; //increases the spot in the array that the result is stored to
			}
		printf("The Base-%d form of %d is ", base,dec);
		for (j=i-1; j>=0; j = j - 1){ //need j=i-1 bc i increases, then loop finishes, so it will try to print a value that wasn't set
			printf("%c ",result[j]);
			}
		}

	if (argc == 3){
		sscanf(argv[1],"%d", &dec); //taking input of decimal
		sscanf(argv[2],"%d", &base); //taking input of base

		if (base < 2 || base > 36) {
			printf("base value must be between 2 and 36\n");
			return(0);
			}

		if (base >= 2 && base <= 36) {
			quot = dec; // will only =dec on first run of the while loop
			while (quot != 0) {
				result[i] = convert[(quot % base)]; // stores remainder to array
				quot = (quot/base); //divides quotient by base
				i = i + 1; //increases the spot in the array that the result is stored to
						}
				printf("The Base-%d form of %d is ", base,dec);
				for (j=i-1; j>=0; j = j - 1){
					printf("%c ",result[j]);
					}
			}
		}

	return 0;

}
