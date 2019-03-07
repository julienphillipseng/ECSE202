/*=========================================================================
 * lookup.c
 *
 * Author: Julien Phillips
 * Student ID: 260804197
 *
 * This program takes an input from the command line, those inputs being the program name, two
 * text files (containing the names, student IDs, and grades of all the students) and the Last
 * Name of a student. It then prints out the entire student record for that particular student.
 *
 =========================================================================*/

#define MAXRECORDS 100
#define MAXNAMELENGTH 15
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//Define structure to hold student data
struct StudentRecord
{
	char FirstNames[MAXNAMELENGTH];
	char LastNames[MAXNAMELENGTH];
	int IDNums;
	int Marks;
};


int main(int argc, char * argv[]) {

	struct StudentRecord SRecords[MAXRECORDS];
    	int numrecords, nummarks, recordnum;

    if (argc != 3){ //Should be argc != 4 in working program as the command line takes 4 arguments
    	return 0;
    }

    if (argc == 3) { //Should be argc ==4 in working program as the command line takes 4 arguments

	//Read in Names and ID data
	FILE * NamesIDsDataFile;
	if((NamesIDsDataFile = fopen(argv[1], "r")) == NULL){
		printf("Can't read from file %s\n", argv[1]);
		exit(1);
	}

	numrecords=0;
    	while (fscanf(NamesIDsDataFile,"%s%s%d",&(SRecords[numrecords].FirstNames[0]),
		      				&(SRecords[numrecords].LastNames[0]),
		      				&(SRecords[numrecords].IDNums)) != EOF) {
	  numrecords++;
 	}

	fclose(NamesIDsDataFile);

	//Read in marks data
	FILE * MarksDataFile;
	if((MarksDataFile = fopen(argv[2], "r")) == NULL){
		printf("Can't read from file %s\n", argv[2]);
		exit(1);
	}
	nummarks=0;
	while(fscanf(MarksDataFile,"%d",&(SRecords[nummarks].Marks)) != EOF) {
	    nummarks++;
	}

	fclose(MarksDataFile);

	//Print out data as read in
	for(recordnum=0;recordnum<numrecords;recordnum++){
		printf("%s %s %d %d\n",SRecords[recordnum].FirstNames,SRecords[recordnum].LastNames,SRecords[recordnum].IDNums, SRecords[recordnum].Marks);
	}


	qsort(0,numrecords-1,SRecords);

	printf("Sorted Array: ");
	  for(recordnum=0;recordnum<numrecords;recordnum++){
		  printf("%s %s %d %d\n",SRecords[recordnum].FirstNames,SRecords[recordnum].LastNames,SRecords[recordnum].IDNums, SRecords[recordnum].Marks);
	  }

    }
}

void qsort(int first, int last, struct StudentRecord SRecords[]){
	int pivot, i, j, temp;

	if(first < last){
		pivot=first;
	    i=first;
	    j=last;

	    while(i<j){
	    		while( strcmp (SRecords[i].LastNames,SRecords[j].LastNames) < pivot)
	    			i=i+1;
	    		while( strcmp (SRecords[i].LastNames,SRecords[j].LastNames) > pivot)
	    			j= j-1;
	    		if( i < j ){
	    			temp=SRecords[i];
	    			SRecords[i]=SRecords[j];
	    			SRecords[j]=temp;
	    		}
	    }
	    		temp=SRecords[pivot];
	    		SRecords[pivot]=SRecords[j];
	    		SRecords[j]=temp;
	    	    qsort(SRecords,first,j-1);
	    	    qsort(SRecords,j+1,last);
	}
}

/* Couldn't figure this out after hours of trying, and due to other time constraints I wasn't able to complete this program.
 * Any feedback would be great as I struggled a lot trying to complete this.*/
