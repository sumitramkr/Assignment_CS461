#include <omp.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    int sum = 0;
    int a[100];

    for (int i = 0; i < 100; i++)
    {
        a[i] = i + 1;
    }
    int total_threads_used;
// Create a parallel region with 5 threads and reduce the partial sum's values
#pragma omp parallel num_threads(5) reduction(+ \
                                              : sum)
    {
        total_threads_used = omp_get_num_threads(); // Get the total threads used
#pragma omp for
        for (int i = 0; i < 100; i++)
        {
            sum = sum + a[i];
        }
        printf("Current thread is %d and SUM %d\n", omp_get_thread_num(), sum);
    }
    printf("Number of threads = %d\n", total_threads_used);
    printf("The total sum is %d\n\n\n", sum);
}