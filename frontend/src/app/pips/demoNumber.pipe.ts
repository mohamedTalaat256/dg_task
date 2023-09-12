import { Pipe, PipeTransform } from "@angular/core";


@Pipe({name: 'demoNumber'})
export class DemoNumber implements PipeTransform {
  transform(value) : any {
    let res = [];
    for (let i = 0; i < value; i++) {
        res.push(i);
      }

      console.log(res);
      return res;
  }
}