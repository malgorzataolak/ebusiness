import { Pipe, PipeTransform } from '@angular/core';


@Pipe({ 
    name:'myfilter'
})

export class MyFilterPipe implements PipeTransform{
    transform(items: Array<any>, category: number): Array<any> {
        return items.filter(item => item.categoryID === category);
    }
}
