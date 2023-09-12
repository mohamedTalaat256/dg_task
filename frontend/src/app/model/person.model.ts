import { Address } from "./address.model";
import { Email } from "./email.model";
import { PassportNumber } from "./passportNumber.model";
import { Phone } from "./phone.model";

export class Person{
    constructor(
        
        public gender: string,
        public title: string,
        public firstName: number,
        public lastName: number,
        public ssn: string,
        public passportNumber: PassportNumber,
        public email: Email,
        public phones: Phone[],
        public addresses: Address[]
    ){}
}