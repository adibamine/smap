import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/publications'

import PostLinkForm from 'components/publications/facebook/PostLinkForm'
import PostForm from 'components/publications/facebook/PostForm'
import TweetForm from 'components/publications/twitter/TweetForm'


class CreatePublicationCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    render() {
        let loading = this.props.ajaxBegin ? <div>Loading...</div> : "";
        return (
            <div>
                <PostLinkForm createPostLink = { this.props.createPostLink } />
                <hr />
                <PostForm createPost = { this.props.createPost } />
                <hr />
                <TweetForm createTweet = { this.props.createTweet } />
            </div>
        );
    }
};


function mapStateToProps(state) {
    return {
        savedPost: state.publication.get('saved'),
        ajaxBegin: state.publication.get('ajaxBegin')
    }
}

export const CreatePublication = connect(mapStateToProps, actionCreators)(CreatePublicationCmp)