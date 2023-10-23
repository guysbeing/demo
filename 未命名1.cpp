#include <stdio.h>
#include<limits.h>

int absVal(int x)
{
    int sign = (x >> 31) & 1;
    return (x  ^ (x >> 31)  ) +sign;
}
int addOK(int x, int y)
{
    int z = x + y;
    int sign_x = (x >> 31)&1;
    int sign_y = (y >> 31)&1;
    int sign_z = (z >> 31)&1;
    int a = sign_x ^ sign_y;
    int b = !(sign_z ^ sign_x);
    return ((a + (~a & b)) & 0x1);
}
int conditional(int x, int y, int z)
{
    int a = ! !x;
    int mark1 = (a << 31) >> 31;
    int mark2 = ~mark1;
    return (mark1 & y) + (mark2 & z);
}
typedef unsigned float_bits;

float_bits float_abs(float_bits f)
{
    unsigned a = (f >> 23) & 0xFF;
    unsigned b = f & 0x7FFFFF;
    unsigned c = !!((a ^ 0xFF) | !b);
    unsigned mark = ~(c << 31) ;
    return mark & f;
}

int main()
{
    printf("absVal test:\n");
    printf("absVal(-5)=%d\n", absVal(-5));
    printf("absVal(12)=%d\n", absVal(12));

    printf("addOK test:\n");
    printf("addOK(0x80000000,0x80000000)=%d\n", addOK(0x80000000, 0x80000000));
    printf("addOK(0x80000000,0x70000000)=%d\n", addOK(0x80000000, 0x70000000));

    printf("conditional test\n");
    printf("conditional(2,4,5)=%d\n", conditional(2, 4, 5));
    printf("conditional(0,4,5)=%d\n", conditional(0, 4, 5));

    printf("float_abs test:\n");
    printf("float_abs(0x42c88000)=%8x\n", float_abs(0x42c88000));
    printf("float_abs(0xc1c90000)=%8x\n", float_abs(0xc1c90000));
    printf("float_abs(0xffff0000)=%8x\n", float_abs(0xffff0000));
}
