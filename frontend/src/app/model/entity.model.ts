import { Address } from "./address.model";
import { Person } from "./person.model";
import { Phone } from "./phone.model";

export class Entity{
    constructor(
        public id: number,
        public name: string,
        public commercialName: string,
        public business: string,
        public phones: Phone[],
        public addresses: Address[],
        public directors: Person[] ){}
}