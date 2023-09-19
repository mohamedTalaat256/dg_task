import { Address } from "../model/address.model";
import { Email } from "../model/email.model";
import { PassportNumber } from "../model/passportNumber.model";
import { Phone } from "../model/phone.model";


export class DirectorSaveRequest{
    constructor(
        public gender: string,
        public title: string,
        public firstName: string,
        public lastName: string,
        public ssn: string,
        public passportNumber: PassportNumber,
        public email: Email,
        public phones: Phone[],
        public addresses: Address[],
        public entityId: number,
        
        ){}
}


