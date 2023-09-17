import { Address } from "../model/address.model";
import { Phone } from "../model/phone.model";


export class EntitySaveRequest{
    constructor(
        public name: string,
        public commercialName: string,
        public business: string,
        public phones: Phone[],
        public addresses: Address[] ){}
}