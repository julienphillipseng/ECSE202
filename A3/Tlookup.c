/*=========================================================================
 * Tlookup.c
 *
 * Author: Julien Phillips
 * Student ID: 260804197
 *
 * This program takes an input from the command line, those inputs being the
 * name of the program, both text files containing the student information,
 * and the last name of a student. It creates a binary tree containing the
 * Student Records, then traverses the binary tree in order to make an array
 * containing the sorted version by Last Name. Then, it searches through the
 * sorted array using a binary search function, in order to find the student
 * record desired.
 *
 =========================================================================*/

#define MAXRECORDS 100
#define MAXNAMELENGTH 15
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//Structure that holds the Student Data
struct StudentRecord
{
	char FirstNames[MAXNAMELENGTH];
	char LastNames[MAXNAMELENGTH];
	int IDNums;
	int Marks;
};

//Structure that holds Binary Tree
struct BinT
{
	struct StudentRecord* data;
	struct BinT* left;
	struct BinT* right;
};

//Inserting new nodes into the binary tree
void insert_node(struct BinT** root, struct StudentRecord *newdata)
{
	if(*root == NULL)
	{
		struct BinT* cur_root = (struct BinT*)malloc(sizeof(struct BinT));
		cur_root->data = newdata;
		cur_root->left = NULL;
		cur_root->right = NULL;
		(*root) = cur_root;
	}

	else if(strcasecmp((*root)->data->LastNames, newdata->LastNames) > 0)
	{
		insert_node (&((*root)->left),newdata);
	}

	else
	{
		insert_node (&((*root)->right), newdata);
	}
}

//Creating sorted array
int TreeArr(struct BinT** bint, struct StudentRecord **records, int ArrayElem)
{
	if((*bint)->left != 0)
		ArrayElem = TreeArr(&(*bint)->left, records, ArrayElem);
	records[ArrayElem++] = (*bint)->data;
	if((*bint)->right != 0)
		ArrayElem = TreeArr(&(*bint)->right, records, ArrayElem);
	return ArrayElem;
}

//Binary search
void binary(struct StudentRecord* SRecords[], int n, char *ind)
{
	int first = 0;
	int last = n - 1;
	int middle;
	while(first <= last)
	{
		middle = (first + last)/2;
			if(strcasecmp(SRecords[middle]->LastNames, ind) == 0)
			{
				printf("The Following Record Was Found: \nName: %s %s \nStudent ID: %d \nStudent Grade: %d \n", SRecords[middle]->FirstNames, SRecords[middle]->LastNames, SRecords[middle]->IDNums, SRecords[middle]->Marks);
				exit(0);
			}

			else if (strcasecmp(SRecords[middle]->LastNames, ind) < 0)
			{
				first = middle + 1;
			}

			else if (strcasecmp(SRecords[middle]->LastNames, ind) > 0)
			{
				last = middle - 1;
			}

			if(first > last)
			{
				printf("No Record was Found for the Student with Last Name %s\n", ind);
				exit(0);
			}
		}
}

int main(int argc, char * argv[])
{
	if(argc == 4)
	{
		struct StudentRecord SRecords[MAXRECORDS];
		struct BinT *rtree = NULL;
		int numrecords;

		//Reading in Names, IDs, marks
		FILE * NamesIDsDataFile;
			if((NamesIDsDataFile = fopen(argv[1], "r")) == NULL)
			{
				printf("Can't read from file %s\n", argv[1]);
				exit(1);
			}
		FILE * MarksDataFile;
			if((MarksDataFile = fopen(argv[2], "r")) == NULL)
			{
				printf("Can't read from file %s\n", argv[2]);
				exit(1);
			}
		numrecords=0;
		    while (fscanf(NamesIDsDataFile,"%s%s%d",&(SRecords[numrecords].FirstNames[0]),&(SRecords[numrecords].LastNames[0]),&(SRecords[numrecords].IDNums)) != EOF
		    		&& fscanf(MarksDataFile,"%d",&(SRecords[numrecords].Marks)) != EOF)
		    {
		    	numrecords++;
		    }

		fclose(NamesIDsDataFile);
		fclose(MarksDataFile);

		char ind[MAXNAMELENGTH];
			sscanf(argv[3], "%s", &ind);

		//Making pSRecord and filling it with pointers from SRecords
		struct StudentRecord *pSRecord[MAXRECORDS];
			for(int j = 0; j < numrecords; j++)
			{
				pSRecord[j] = &SRecords[j];
			}

		//Creating Binary Tree
		for(int n = 0; n < numrecords; n++)
		{
			insert_node(&rtree, pSRecord[n]);
		}

		struct StudentRecord **RecArray = malloc(numrecords*sizeof(struct BinaryTree*));

		TreeArr(&rtree, RecArray, 0);

		//Binary Search
		binary(RecArray, numrecords, ind);

		return EXIT_SUCCESS;
	}

	else
	{
		printf("Wrong Number of Arguments\n");
		return 0;
	}
}
