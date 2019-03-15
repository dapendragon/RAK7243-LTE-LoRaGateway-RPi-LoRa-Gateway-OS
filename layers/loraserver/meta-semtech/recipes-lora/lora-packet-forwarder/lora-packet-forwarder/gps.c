#include <unistd.h>
#include <stdint.h>
#include <stdio.h>
#include <fcntl.h>
#include <linux/i2c-dev.h>
#include <errno.h>
#define I2C_ADDR 0x42
int main(void)
{ 

  int file;
  int adapter_nr = 1; 
  char filename[20]; 
  int  res;
  char buf[10];

  snprintf(filename, 19, "/dev/i2c-%d", adapter_nr);

  file = open(filename, O_RDWR);
  if (file < 0) {
    exit(1);
  }

  int addr = 0x42; /* I2C 从机地址 */
  if (ioctl(file, I2C_SLAVE, addr) < 0) {
 
    exit(1);
  }
while(1){

int low=8;
uint8_t val;

//	if(low > 0){
//		for (int i=0;i<low;i++){
		 res = read(file,&val,1);
		  if (res < 0) {
		    printf("i2c_smbus_read_byte_data err 333\r\n");
		  } else {
		    printf("%c",val);
		  }
//		}
//	}

	usleep(10000);
}
//   i2c_smbus_read_i2c_block_data

}
