import { Author } from './author';

export class Book {
    idBook: number;
    nameBook: String;
    nameImage: String;
    nameCategory: string;
    nameAuthor:String;
    nameProducer:String;
    summaryBook: String;
    idCategory: number;
    idAuthor: number;
    idProducer: number;
    isDelete:boolean;
    author:Author;
    createDay: Date;
}