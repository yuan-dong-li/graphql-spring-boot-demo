package org.example.app.schema;

import graphql.schema.*;
import org.example.app.dao.UserQueryRepository;
import org.example.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;

@Component
public class UserGraphSchema {

    @Autowired
    private UserQueryRepository userQueryRepository;

    private GraphQLSchema schema;
    private GraphQLOutputType userType;

    public UserGraphSchema() {
        initOutputType();
        schema = GraphQLSchema.newSchema().query(
                GraphQLObjectType.newObject()
                        .name("GraphQuery")
                        .field(createUsersField())
                        .field(createUserField()).build())
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

    private void initOutputType() {
        userType = GraphQLObjectType.newObject()
                .name("User")
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("id").type(GraphQLInt).build())
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("age").type(GraphQLInt).build())
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("sex").type(GraphQLInt).build())
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("name").type(GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition()
                        .name("pic").type(GraphQLString).build())
                .build();
    }

    private GraphQLFieldDefinition createUserField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("user")
                .argument(GraphQLArgument.newArgument()
                        .name("id")
                        .type(GraphQLInt).build())
                .type(userType)
                .dataFetcher(env -> {
                    int id = env.getArgument("id");

                    User user = userQueryRepository.getUser(id);
                    return user;
                }).build();
    }

    private GraphQLFieldDefinition createUsersField() {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("users")
                .argument(GraphQLArgument.newArgument()
                        .name("page")
                        .type(GraphQLInt).build())
                .argument(GraphQLArgument.newArgument()
                        .name("size")
                        .type(GraphQLInt).build())
                .argument(GraphQLArgument.newArgument()
                        .name("name")
                        .type(GraphQLString).build())
                .type(new GraphQLList(userType))
                .dataFetcher(env -> {
                    int page = env.getArgument("page");
                    int size = env.getArgument("size");
                    String name = env.getArgument("name");

                    List<User> users = userQueryRepository.getUsers(page, size, name);
                    return users;
                }).build();
    }

}
