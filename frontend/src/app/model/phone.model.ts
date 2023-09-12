export class Phone{
    constructor(
        public tphContactType: string ,
        public tphCommunicationType: string,
        public tphCountryPrefix: string,
        public tphNumber: string,
        public tphExtension: string,
        public comments: string
    ){}
}