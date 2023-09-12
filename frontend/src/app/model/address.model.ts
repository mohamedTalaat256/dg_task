export class Address{
    constructor(
        public addressType: string ,
        public address: string,
        public town: string,
        public city: string,
        public zip: string,
        public countryCode: string,
        public state: string,
        public comments: string
    ){}
}