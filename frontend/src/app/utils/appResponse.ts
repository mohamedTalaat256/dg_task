
export class AppResponse{
  constructor(
    public data: any ,
    public message: string,
    public success: boolean,
    public statusCode: number,
    
){}
 
}
