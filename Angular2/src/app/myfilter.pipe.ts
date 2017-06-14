import { Pipe, PipeTransform } from '@angular/core';


@Pipe({ 
    name:'myfilter'
})

export class MyFilterPipe implements PipeTransform{
    transform(items: Array<any>, kategoria: string): Array<any> {
        return items.filter(item => item.kategoria === kategoria);
    }
}
