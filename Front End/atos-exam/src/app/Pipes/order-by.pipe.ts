import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'orderBy'})
export class OrderByPipe implements PipeTransform {
  transform(array: any[], key: string, reverse: boolean = false): any[] {
    if (!array || array.length === 0) {
      return [];
    }

    if (!key) {
      return array;
    }

    const sortedArray = array.sort((a: any, b: any) => {
      const x = a[key];
      const y = b[key];

      if (x < y) {
        return reverse ? 1 : -1;
      } else if (x > y) {
        return reverse ? -1 : 1;
      } else {
        return 0;
      }
    });

    return sortedArray;
  }
}
