import { Address } from "./address.model";
import { Email } from "./email.model";
import { Entity } from "./entity.model";
import { PassportNumber } from "./passportNumber.model";
import { Phone } from "./phone.model";

export class Person{
    constructor(
        
        public id: number,
        public gender: string,
        public title: string,
        public firstName: string,
        public lastName: string,
        public ssn: string,
        public passportNumber: PassportNumber,
        public email: Email,
        public phones: Phone[],
        public addresses: Address[],
        public entity: Entity
    ){}
}


export class EntityDto{
    constructor(
        
        public id: number,
        public gender: string,
        public title: string,
        public firstName: string,
        public lastName: string,
        public ssn: string,
        public passportNumber: PassportNumber,
        public email: Email,
        public phones: Phone[],
        public addresses: Address[],
        public entity: Entity
    ){}
}